package dev.dotingo.bitbox.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {
    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<Void>

    @GET("/auth/profile")
    suspend fun getProfile(): Response<UserProfile>

    @GET ("/auth/refresh")
    suspend fun refresh(): Response<Unit>

    @GET("/users")
    suspend fun getUserById(
        @Query("_id") userId: String
    ): Response<UserProfile>

    @GET("/storages")
    suspend fun getStorages(): Response<List<Storage>>
}