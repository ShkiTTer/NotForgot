package com.example.todo.domain.repository

import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User
import retrofit2.Call

interface ITaskRepository {
    suspend fun registerUser(newUser: NewUser): User
}