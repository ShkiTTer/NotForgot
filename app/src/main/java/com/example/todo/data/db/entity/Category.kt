package com.example.todo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val categoryId: Int,
    val categoryName: String,
    val categorySynchronized: Boolean
)