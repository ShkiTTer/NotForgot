package com.example.todo.domain.entity

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val done: Int,
    val created: Int,
    val deadline: Int?,
    val priority: Priority,
    val category: Category
)