package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.RegisterUser
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TaskApiService {
    @Headers("Accept: application/json")
    @POST("/register")
    fun registerUser(@Body registerUser: RegisterUser) {}
}