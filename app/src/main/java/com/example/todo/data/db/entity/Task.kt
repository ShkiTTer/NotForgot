package com.example.todo.data.db.entity

import androidx.room.Entity

@Entity
data class Task(
    val title: String,
    val description: String,
    val created: Int,
    val deadline: Int?,
    val category: Category,
    val priority: Priority,
    val done: Int,
    val synchronized: Boolean
)