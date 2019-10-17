package com.example.todo.data.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.Priority
import com.example.todo.domain.entity.Task

interface INetworkRepository {
    suspend fun registerUser(registerUser: RegisterUser): UserToken
    suspend fun login(loginUser: LoginUser): UserToken

    suspend fun getTasks(token: String): List<Task>
    suspend fun getCategories(token: String): List<Category>
    suspend fun getPriorities(token: String): List<Priority>
}