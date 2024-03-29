package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.NewTask
import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.Task
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.Priority
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
    fun getCategories(@Header("Authorization") token: String): Call<List<com.example.todo.data.network.entity.Category>>

    @Headers("Accept: application/json")
    @GET("priorities")
    fun getPriorities(@Header("Authorization") token: String): Call<List<Priority>>

    @Headers("Accept: application/json")
    @POST("tasks")
    fun createTask(@Header("Authorization") token: String, @Body task: NewTask): Call<Task>

    @Headers("Accept: application/json")
    @PATCH("tasks/{id}")
    fun updateTask(@Header("Authorization") token: String, @Body task: NewTask, @Path("id") id: Int = task.id): Call<Unit>

    @Headers("Accept: application/json")
    @DELETE("tasks/{id}")
    fun deleteTask(@Header("Authorization") token: String, @Path("id") id: Int): Call<Unit>

    @Headers("Accept: application/json")
    @POST("categories")
    fun createCategory(@Header("Authorization") token: String, @Body category: Category): Call<Unit>
}