package com.example.fb10_app.network

import com.example.fb10_app.data.ApiResponse
import com.example.fb10_app.data.UserRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserApiService {

    @GET("users.php")
    suspend fun getUsers(): ApiResponse

    @GET("users.php")
    suspend fun getUser(
        @Query("id") id: Int
    ): ApiResponse

    @POST("users.php")
    suspend fun createUser(
        @Body request: UserRequest
    ): ApiResponse

    @PUT("users.php")
    suspend fun updateUser(
        @Query("id") id: Int,
        @Body request: UserRequest
    ): ApiResponse

    @DELETE("users.php")
    suspend fun deleteUser(
        @Query("id") id: Int
    ): ApiResponse
}