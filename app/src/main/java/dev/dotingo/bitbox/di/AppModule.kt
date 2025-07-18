package dev.dotingo.bitbox.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.dotingo.bitbox.data.EncryptedTokenStore
import dev.dotingo.bitbox.model.AuthApi
import dev.dotingo.bitbox.model.AuthInterceptor
import dev.dotingo.bitbox.model.TokenAuthenticator
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://btx.backend.eugek0.ru/"

    // ---------- TokenStore ----------
    @Provides
    @Singleton
    fun provideTokenStore(@ApplicationContext context: Context): EncryptedTokenStore =
        EncryptedTokenStore(context)

    // ---------- OkHttp без токенов (для регистрации/логина) ----------
    @Provides
    @Singleton
    @Named("BaseClient")
    fun provideBaseOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder().build()

    // ---------- Retrofit без токенов ----------
    @Provides
    @Singleton
    @Named("BaseRetrofit")
    fun provideBaseRetrofit(
        @Named("BaseClient") client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    // ---------- AuthApi без токенов (регистрация/логин) ----------
    @Provides
    @Singleton
    @Named("BaseAuthApi")
    fun provideBaseAuthApi(
        @Named("BaseRetrofit") retrofit: Retrofit
    ): AuthApi =
        retrofit.create(AuthApi::class.java)


    // ---------- Клиент только для refresh (докидывает куку) ----------
    @Provides
    @Singleton
    @Named("RefreshClient")
    fun provideRefreshOkHttpClient(
        tokenStore: EncryptedTokenStore
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val cookie = runBlocking { tokenStore.cookieFlow.firstOrNull().orEmpty() }
                val req = chain.request().newBuilder()
                    .apply { if (cookie.isNotBlank()) header("Cookie", cookie) }
                    .build()
                chain.proceed(req)
            }
            .build()

    // ---------- Retrofit для refresh ----------
    @Provides
    @Singleton
    @Named("RefreshRetrofit")
    fun provideRefreshRetrofit(
        @Named("RefreshClient") client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    // ---------- AuthApi только для refresh ----------
    @Provides
    @Singleton
    @Named("RefreshApi")
    fun provideRefreshAuthApi(
        @Named("RefreshRetrofit") retrofit: Retrofit
    ): AuthApi =
        retrofit.create(AuthApi::class.java)


    // ---------- Основной клиент с Authenticator ----------
    @Provides
    @Singleton
    @Named("AuthorizedClient")
    fun provideAuthorizedOkHttpClient(
        tokenStore: EncryptedTokenStore,
        @Named("RefreshApi") refreshApi: AuthApi
    ): OkHttpClient =
        OkHttpClient.Builder()
            // Authenticator подхватит 401, вызовет refreshApi.refresh() с нормальной кукой и повторит запрос
            .authenticator(TokenAuthenticator(tokenStore, refreshApi))
            // Простой интерсептор, чтобы в каждый запрос уходила актуальная кука
            .addInterceptor { chain ->
                val cookie = runBlocking { tokenStore.cookieFlow.firstOrNull().orEmpty() }
                val req = chain.request().newBuilder()
                    .apply { if (cookie.isNotBlank()) header("Cookie", cookie) }
                    .build()
                chain.proceed(req)
            }
            .build()

    // ---------- Retrofit для защищённых запросов ----------
    @Provides
    @Singleton
    @Named("AuthorizedRetrofit")
    fun provideAuthorizedRetrofit(
        @Named("AuthorizedClient") client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    // ---------- AuthApi для обычных защищённых запросов ----------
    @Provides
    @Singleton
    fun provideAuthApi(
        @Named("AuthorizedRetrofit") retrofit: Retrofit
    ): AuthApi =
        retrofit.create(AuthApi::class.java)
}

