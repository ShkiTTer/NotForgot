package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.Task
import retrofit2.Call
import retrofit2.http.*

interface TaskApiService {
    @Headers("Accept: application/json")
    @POST("register")
    fun registerUser(@Body registerUser: RegisterUser): Call<UserToken>

    @Headers("Accept: application/json")
    @POST("login")
    fun login(@Body loginUser: LoginUser): Call<UserToken>

    @Headers("Accept: application/json")
    @GET("tasks")
    fun getTasks(@Header("Authorization") token: String): Call<List<Task>>

    @Headers("Accept: application/json")
    @GET("categories")
    fun getCategories(@Header("Authorization") token: String): Call<List<Category>>
}