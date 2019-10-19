package com.example.todo.data.db.entity

import androidx.room.Entity

@Entity
data class Priority(
    val id: Int,
    val name: String,
    val color: String
)