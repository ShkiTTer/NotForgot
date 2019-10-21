package com.example.todo.data.db.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.todo.data.db.entity.Category

interface CategoryDao {
    @Query("Select * From Category")
    fun getAll(): List<Category>

    @Insert
    fun add(category: Category): Int
}