package com.example.todo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Priority(
    @PrimaryKey val priorityId: Int,
    val priorityName: String,
    val color: String
)