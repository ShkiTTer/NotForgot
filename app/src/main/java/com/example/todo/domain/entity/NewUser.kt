package com.example.todo.domain.entity

data class NewUser(
    var email: String,
    var name: String,
    var password: String,
    var confirmPassword: String
)