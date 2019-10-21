package com.example.todo.domain.repository

import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.Priority
import com.example.todo.domain.entity.Task

interface IDbRepository {
    suspend fun getTasks(): List<Task>
    suspend fun getCategories(): List<Category>
    suspend fun getPriorities(): List<Priority>

    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)

    suspend fun addCategory(category: Category)
}