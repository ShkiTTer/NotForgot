package com.example.todo.data.network.entity

import com.google.gson.annotations.SerializedName

data class NewTask(
    val title: String,
    val description: String,
    val deadline: Long?,
    @SerializedName("category_id") val category: Int,
    @SerializedName("priority_id") val priority: Int,
    val id: Int
)