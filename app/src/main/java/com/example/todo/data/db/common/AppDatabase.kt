package com.example.todo.data.db.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.data.db.dao.CategoryDao
import com.example.todo.data.db.dao.PriorityDao
import com.example.todo.data.db.dao.TaskDao
import com.example.todo.data.db.entity.Category
import com.example.todo.data.db.entity.Priority
import com.example.todo.data.db.entity.Task

@Database(entities = [Task::class, Category::class, Priority::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getPriorityDao(): PriorityDao
}