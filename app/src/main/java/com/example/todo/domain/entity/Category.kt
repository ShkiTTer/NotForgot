package com.example.todo.domain.entity

data class Category(
    val id: Int,
    val name: String,
    var synchronized: Boolean = true
)