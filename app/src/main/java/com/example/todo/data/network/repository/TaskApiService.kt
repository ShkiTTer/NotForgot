package com.example.todo.data.network.repository

import retrofit2.http.POST

interface TaskApiService {
    @POST("/register")
    fun registerUser() {

    }
}