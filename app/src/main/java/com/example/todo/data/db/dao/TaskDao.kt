package com.example.todo.data.db.dao

import androidx.room.*
import com.example.todo.data.db.entity.Task

@Dao
interface TaskDao {
    @Query("Select * From Task")
    fun getAllTasks(): List<Task>

    @Insert
    fun insertTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}