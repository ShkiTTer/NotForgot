package com.example.todo.domain.repository

import com.example.todo.domain.entity.*

interface INetworkRepository {
    suspend fun registerUser(newUser: NewUser): UserToken?
    suspend fun login(loginUser: LoginUser): UserToken?

    suspend fun getTasks(token: String): List<Task>?
    suspend fun getCategories(token: String): List<Category>?
    suspend fun getPriorities(token: String): List<Priority>?

    suspend fun createTask(token: String, task: Task): Int?
    suspend fun updateTask(token: String, task: Task): Unit?
    suspend fun deleteTask(token: String, taskId: Int): Unit?

    suspend fun createCategory(token: String, category: Category): Unit?
}