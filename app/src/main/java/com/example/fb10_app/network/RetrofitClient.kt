package com.example.fb10_app.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    object RetrofitClient {
        private const val BASE_URL = "http://10.0.2.2/android_sample_api/"
        Android App Sample - User Account CRUD Guide
        Instruction guide created by : Joseph Q. Calleja
        val userApi: UserApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserApiService::class.java)
        }
    }
}