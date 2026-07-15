package com.example.fb10_app.data

import com.example.fb10_app.network.RetrofitClient
class UserRepository {
    private val api = RetrofitClient.userApi
    suspend fun getUsers(): List<User> {
        val response = api.getUsers()
        if (!response.success) error(response.message ?: "Unable to load users")
        return response.users.orEmpty()
    }
    suspend fun getUser(id: Int): User {
        val response = api.getUser(id)
        if (!response.success) error(response.message ?: "Unable to load user")
        return response.user ?: error("User not found")
    }
    suspend fun createUser(request: UserRequest) {
        val response = api.createUser(request)
        if (!response.success) error(response.message ?: "Unable to create user")
    }
    suspend fun updateUser(id: Int, request: UserRequest) {
        val response = api.updateUser(id, request)
        if (!response.success) error(response.message ?: "Unable to update user")
    }

    suspend fun deleteUser(id: Int) {
        val response = api.deleteUser(id)
        if (!response.success) error(response.message ?: "Unable to delete user")
    }
}
