package com.example.todo.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.db.entity.Priority

@Dao
interface PriorityDao {
    @Query("Select * From Priority")
    fun getAll(): List<Priority>

    @Query("Delete From Priority")
    fun clear()

    @Insert
    fun addAll(priorities: List<Priority>)
}