package dev.dotingo.bitbox.model

import android.util.Log
import dev.dotingo.bitbox.data.EncryptedTokenStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Named

class TokenAuthenticator @Inject constructor(
    private val tokenStore: EncryptedTokenStore,
    @Named("RefreshApi") private val refreshApi: AuthApi
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // Если уже пытались рефрешиться и снова получили 401 — дальше не пробуем
        if (responseCount(response) > 1) return null

        // Блокируем coroutine, чтобы выдернуть свежие куки
        val currentCookie = runBlocking { tokenStore.cookieFlow.firstOrNull().orEmpty() }
        Log.d("TokenAuth", "Refreshing with cookie: $currentCookie")
        // Запрашиваем новые токены
        val newCookie = runBlocking {
            try {
                val refreshResp = refreshApi.refresh()
                if (!refreshResp.isSuccessful) return@runBlocking null

                val raw = refreshResp.headers().values("Set-Cookie")
                if (raw.isEmpty()) return@runBlocking null

                // Формируем "access=...; refresh=..."
                val combined = raw.joinToString("; ") { it.substringBefore(";") }
                tokenStore.saveCookie(combined)
                combined
            } catch (e: Exception) {
                null
            }
        } ?: return null

        // Строим **новый** запрос — OkHttp сам уйдёт на повтор
        return response.request.newBuilder()
            .header("Cookie", newCookie)
            .build()
    }

    // Сколько раз мы уже получили ответ по этому запросу?
    private fun responseCount(response: Response): Int {
        var res = response.priorResponse
        var count = 1
        while (res != null) {
            count += 1
            res = res.priorResponse
        }
        return count
    }
}
