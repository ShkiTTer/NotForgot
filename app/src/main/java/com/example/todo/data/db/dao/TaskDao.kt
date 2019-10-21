package com.example.todo.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todo.data.db.entity.Task

interface TaskDao {
    @Query("Select * From Task")
    fun getAllTasks(): List<Task>

    @Insert
    fun insertTask(task: Task): Int

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)
}