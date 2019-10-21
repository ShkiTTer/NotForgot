package com.example.todo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    val title: String,
    val description: String,
    val created: Date,
    val deadline: Date?,
    val category: Category,
    val priority: Priority,
    val done: Int,
    val synchronized: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)