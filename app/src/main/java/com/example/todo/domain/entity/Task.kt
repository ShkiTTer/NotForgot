package com.example.todo.domain.entity

data class Task(
    val title: String,
    val description: String,
    val done: Int,
    val created: Int,
    val deadline: Int?,
    val priority: Priority,
    val category: Category,
    val id: Int = 0
)