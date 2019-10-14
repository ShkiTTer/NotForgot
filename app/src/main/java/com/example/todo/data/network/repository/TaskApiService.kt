package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface TaskApiService {
    @Headers("Accept: application/json")
    @POST("register")
    fun registerUser(@Body registerUser: RegisterUser): Call<UserToken>
}