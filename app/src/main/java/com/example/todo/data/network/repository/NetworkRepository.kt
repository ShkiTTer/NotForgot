package com.example.todo.data.network.repository

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.data.repository.INetworkRepository
import retrofit2.await

class NetworkRepository(private val taskApiService: TaskApiService) : INetworkRepository {
    override suspend fun registerUser(registerUser: RegisterUser): UserToken =
        taskApiService.registerUser(registerUser).await()
}