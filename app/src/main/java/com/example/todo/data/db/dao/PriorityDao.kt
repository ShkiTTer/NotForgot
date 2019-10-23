package com.example.todo.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.todo.data.db.entity.Priority

@Dao
interface PriorityDao {
    @Query("Select * From Priority")
    fun getAll(): List<Priority>
}