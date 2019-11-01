package com.example.todo.data.network.entity

import com.example.todo.domain.entity.Priority

data class Task(
    val title: String,
    val description: String,
    val done: Int,
    val created: Long,
    val deadline: Long?,
    val priority: Priority,
    val category: Category,
    val id: Int = 0
)