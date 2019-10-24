package com.example.todo.domain.entity

import java.util.*

data class Task(
    val title: String,
    val description: String,
    val done: Int,
    val created: Date,
    val deadline: Date?,
    val priority: Priority,
    val category: Category,
    val id: Int = 0,
    var synchronized: Boolean = true
)