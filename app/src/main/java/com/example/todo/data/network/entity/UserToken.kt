package com.example.todo.data.network.entity

import com.google.gson.annotations.SerializedName

data class UserToken(
    @SerializedName("api_token") val token: String
)