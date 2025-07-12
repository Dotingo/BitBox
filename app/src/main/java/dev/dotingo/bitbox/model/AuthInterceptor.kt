package dev.dotingo.bitbox.model

import android.content.Context
import dev.dotingo.bitbox.data.EncryptedTokenStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthInterceptor(
    private val tokenStore: EncryptedTokenStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val cookie = runBlocking { tokenStore.cookieFlow.firstOrNull() }
        val request = if (!cookie.isNullOrBlank()) {
            chain.request().newBuilder()
                .addHeader("Cookie", cookie)
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
}
