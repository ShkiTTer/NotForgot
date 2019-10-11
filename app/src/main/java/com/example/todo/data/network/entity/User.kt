package com.example.todo.data.network.entity

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val email: String,
    val name: String,
    @SerializedName("api_token") val token: String
)