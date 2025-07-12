package dev.dotingo.bitbox.model

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<Void>

    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<Void>

}