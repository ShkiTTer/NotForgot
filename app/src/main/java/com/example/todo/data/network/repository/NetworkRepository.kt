package com.example.todo.data.network.repository

import com.example.todo.data.network.NetworkConstants
import com.example.todo.data.network.mapper.NetworkMapper
import com.example.todo.domain.entity.*
import com.example.todo.domain.repository.INetworkRepository
import retrofit2.await

class NetworkRepository(private val taskApiService: TaskApiService) :
    INetworkRepository {
    override suspend fun registerUser(newUser: NewUser): UserToken =
        NetworkMapper.userTokenFromNetwork(
            taskApiService.registerUser(
                NetworkMapper.newUserToNetwork(
                    newUser
                )
            ).await()
        )

    override suspend fun login(loginUser: LoginUser): UserToken =
        NetworkMapper.userTokenFromNetwork(taskApiService.login(loginUser).await())

    override suspend fun getTasks(token: String): List<Task> =
        NetworkMapper.taskListFromNetwork(taskApiService.getTasks("${NetworkConstants.TOKEN_HEADER} $token").await())

    override suspend fun getCategories(token: String): List<Category> =
        taskApiService.getCategories("${NetworkConstants.TOKEN_HEADER} $token").await()

    override suspend fun getPriorities(token: String): List<Priority> =
        taskApiService.getPriorities("${NetworkConstants.TOKEN_HEADER} $token").await()

    override suspend fun createTask(token: String, task: Task) {
        taskApiService.createTask(
            "${NetworkConstants.TOKEN_HEADER} $token",
            NetworkMapper.newTask(task)
        ).await()
    }

    override suspend fun updateTask(token: String, task: Task) {
        taskApiService.updateTask(
            "${NetworkConstants.TOKEN_HEADER} $token",
            NetworkMapper.newTask(task)
        ).await()
    }

    override suspend fun deleteTask(token: String, taskId: Int) {
        taskApiService.deleteTask("${NetworkConstants.TOKEN_HEADER} $token", taskId).await()
    }
}
