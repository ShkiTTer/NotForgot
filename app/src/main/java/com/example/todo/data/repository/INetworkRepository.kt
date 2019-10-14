package com.example.todo.data.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken

interface INetworkRepository {
    suspend fun registerUser(registerUser: RegisterUser): UserToken
}