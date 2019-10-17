package com.example.todo.domain.repository

import com.example.todo.domain.entity.*

interface ITaskRepository {
    suspend fun saveToken(token: String?)
    suspend fun getToken(): String?

    suspend fun registerUser(newUser: NewUser): UserToken
    suspend fun login(loginUser: LoginUser): UserToken

    suspend fun getTasks(token: String): List<Task>
    suspend fun getCategories(token: String): List<Category>
    suspend fun getPriorities(token: String): List<Priority>
}