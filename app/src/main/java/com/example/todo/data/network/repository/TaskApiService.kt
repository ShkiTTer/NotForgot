package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.NewTask
import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.Priority
import com.example.todo.domain.entity.Task
import okhttp3.ResponseBody
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

    @Headers("Accept: application/json")
    @GET("priorities")
    fun getPriorities(@Header("Authorization") token: String): Call<List<Priority>>

    @Headers("Accept: application/json")
    @POST("tasks")
    fun createTask(@Header("Authorization") token: String, @Body task: NewTask): Call<Unit>
}