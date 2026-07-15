package com.example.fb10_app.data

import com.google.gson.annotations.SerializedName
data class User(
    val id: Int,
    val username: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("middle_name") val middleName: String?,
    val email: String,
    val photo: String?,
    @SerializedName("date_created") val dateCreated: String?
)

annotation class SerializedName(val value: String)

data class UserRequest(
    val username: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("middle_name") val middleName: String?,
    val email: String,
    val password: String?,
    val photo: String?
)
data class ApiResponse(
    val success: Boolean,
    val message: String?,
    val id: Int?,
    val user: User?,
    val users: List<User>?
)