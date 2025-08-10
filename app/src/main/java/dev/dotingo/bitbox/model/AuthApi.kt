package dev.dotingo.bitbox.model

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
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

    @GET("/storages/{storageid}")
    suspend fun getStorageById(
        @Path("storageid") storageId: String
    ): Response<Storage>

    @GET("/storages/user/{userid}")
    suspend fun getUserStorages(
        @Path("userid") userId: String
    ): Response<List<Storage>>

    @GET("/entities/{storageid}")
    suspend fun getEntities(
        @Path("storageid") storageId: String
    ): Response<EntitiesResponse>

    @Multipart
    @POST("/entities/{storageid}")
    suspend fun uploadFile(
        @Path("storageid") storageId: String,
        @Part entities: MultipartBody.Part,       // сам файл
        @Part("metadata") metadata: RequestBody,  // JSON метаданные
        @Part("parent") parent: RequestBody       // id папки
    ): Response<Unit>
}
