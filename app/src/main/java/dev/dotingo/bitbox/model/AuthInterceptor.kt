package dev.dotingo.bitbox.model

import android.util.Log
import dev.dotingo.bitbox.data.EncryptedTokenStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStore: EncryptedTokenStore,
    private val refreshApi: AuthApi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // Если это запрос за новым токеном — не пытаемся рефрешить, просто идём дальше
        if (request.url.encodedPath == "/auth/refresh") {
            return chain.proceed(request)
        }

        // Берём текущие куки
        val cookie = runBlocking { tokenStore.cookieFlow.firstOrNull() }
        // Делаем запрос с куки (если есть)
        val withCookie = request.newBuilder()
            .apply { if (!cookie.isNullOrBlank()) addHeader("Cookie", cookie) }
            .build()

        var response = chain.proceed(withCookie)

        if (response.code == 401) {
            response.close()

            // Запросим новый токен (он пойдёт через ветку выше и не зациклится)
            val newCookie = runBlocking { refreshToken() }
            if (!newCookie.isNullOrBlank()) {
                val retry = request.newBuilder()
                    .addHeader("Cookie", newCookie)
                    .build()
                response = chain.proceed(retry)
            }
        }

        return response
    }


    private suspend fun refreshToken(): String? {
        return try {
            val response = refreshApi.refresh()
            if (response.isSuccessful) {
                // ВАЖНО: тут используем headers("Set-Cookie"), чтобы получить все куки
                val rawCookies = response.headers().values("Set-Cookie") // List<String>

                if (rawCookies.isNotEmpty()) {
                    // Превращаем список Set-Cookie в "access=...; refresh=..."
                    val combinedCookie = rawCookies.joinToString(separator = "; ") { header ->
                        header.substringBefore(";") // оставляем только "ключ=значение"
                    }
                    tokenStore.saveCookie(combinedCookie)
                    combinedCookie
                } else null
            } else null
        } catch (e: Exception) {
            null
        }
    }
}
