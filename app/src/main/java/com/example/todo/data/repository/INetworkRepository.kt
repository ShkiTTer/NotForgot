package com.example.todo.data.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.User

interface INetworkRepository {
    suspend fun registerUser(registerUser: RegisterUser): User
}