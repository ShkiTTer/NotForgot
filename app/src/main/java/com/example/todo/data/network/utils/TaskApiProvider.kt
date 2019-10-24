package com.example.todo.data.network.utils

import com.example.todo.data.network.NetworkConstants
import com.example.todo.data.network.repository.TaskApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskApiProvider {
    fun create(): TaskApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(TaskApiService::class.java)
    }
}