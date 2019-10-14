package com.example.todo.domain.repository

import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.UserToken

interface ITaskRepository {
    suspend fun registerUser(newUser: NewUser): UserToken
    suspend fun saveToken(token: String?)
    suspend fun getToken(): String?

    suspend fun login(loginUser: LoginUser): UserToken
}