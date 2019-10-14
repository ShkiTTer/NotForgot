package com.example.todo.domain.entity

data class Task(
    val id: Int,
    val title: String,
    val description: String?,
    val done: Int,
    val created: Long,
    val deadline: Long?,
    val priority: Priority?,
    val category: Category?
)