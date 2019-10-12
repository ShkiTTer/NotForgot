package com.example.todo.domain.entity

data class NewUser(
    val email: String,
    val name: String,
    val password: String,
    val confirmPassword: String
)