package com.example.todo.data.network.repository

import com.example.todo.data.DataConstants
import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.data.repository.INetworkRepository
import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.Task
import retrofit2.await

class NetworkRepository(private val taskApiService: TaskApiService) : INetworkRepository {
    override suspend fun registerUser(registerUser: RegisterUser): UserToken =
        taskApiService.registerUser(registerUser).await()

    override suspend fun login(loginUser: LoginUser): UserToken =
        taskApiService.login(loginUser).await()

    override suspend fun getTasks(token: String): List<Task> =
        taskApiService.getTasks("${DataConstants.TOKEN_HEADER} $token").await()
}
