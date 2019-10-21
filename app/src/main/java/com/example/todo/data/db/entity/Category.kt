package com.example.todo.data.db.entity

import androidx.room.Entity

@Entity
data class Category(
    val id: Int,
    val name: String
)