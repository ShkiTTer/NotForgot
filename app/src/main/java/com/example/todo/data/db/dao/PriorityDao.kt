package com.example.todo.data.db.dao

import androidx.room.Query
import com.example.todo.data.db.entity.Priority

interface PriorityDao {
    @Query("Select * From Priority")
    fun getAll(): List<Priority>
}