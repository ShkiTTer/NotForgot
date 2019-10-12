package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.User
import com.example.todo.data.repository.INetworkRepository

class NetworkRepository(private val taskApiService: TaskApiService) : INetworkRepository {
    override fun registerUser(registerUser: RegisterUser): User =
        taskApiService.registerUser(registerUser)
}