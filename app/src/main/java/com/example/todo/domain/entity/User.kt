package com.example.todo.domain.entity

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val token: String
)