package com.example.todo.data.network.repository

import com.example.todo.data.network.NetworkConstants
import com.example.todo.data.network.mapper.NetworkMapper
import com.example.todo.domain.entity.*
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.utils.NetworkStateUtil
import retrofit2.await

class NetworkRepository(
    private val taskApiService: TaskApiService,
    private val networkStateUtil: NetworkStateUtil
) :
    INetworkRepository {
    override suspend fun registerUser(newUser: NewUser): UserToken? {
        return if (networkStateUtil.isOnline)
            NetworkMapper.userTokenFromNetwork(
                taskApiService.registerUser(
                    NetworkMapper.newUserToNetwork(
                        newUser
                    )
                ).await()
            )
        else null
    }

    override suspend fun login(loginUser: LoginUser): UserToken? {
        return if (networkStateUtil.isOnline)
            NetworkMapper.userTokenFromNetwork(taskApiService.login(loginUser).await())
        else null
    }

    override suspend fun getTasks(token: String): List<Task>? {
        return if (networkStateUtil.isOnline) {
            NetworkMapper.taskListFromNetwork(taskApiService.getTasks("${NetworkConstants.TOKEN_HEADER} $token").await())
        } else null
    }


    override suspend fun getCategories(token: String): List<Category>? {
        return if (networkStateUtil.isOnline)
            taskApiService.getCategories("${NetworkConstants.TOKEN_HEADER} $token").await().map {
                NetworkMapper.categoryToModel(it)
            }
        else null
    }


    override suspend fun getPriorities(token: String): List<Priority>? {
        return if (networkStateUtil.isOnline)
            taskApiService.getPriorities("${NetworkConstants.TOKEN_HEADER} $token").await()
        else null
    }


    override suspend fun createTask(token: String, task: Task): Int? {
        return if (networkStateUtil.isOnline) {
            taskApiService.createTask(
                "${NetworkConstants.TOKEN_HEADER} $token",
                NetworkMapper.newTask(task)
            ).await().id
        } else null
    }

    override suspend fun updateTask(token: String, task: Task): Unit? {
        return if (networkStateUtil.isOnline)
            taskApiService.updateTask(
                "${NetworkConstants.TOKEN_HEADER} $token",
                NetworkMapper.newTask(task)
            ).await()
        else null
    }

    override suspend fun deleteTask(token: String, taskId: Int): Unit? {
        return if (networkStateUtil.isOnline)
            taskApiService.deleteTask("${NetworkConstants.TOKEN_HEADER} $token", taskId).await()
        else null
    }

    override suspend fun createCategory(token: String, category: Category): Unit? {
        return if (networkStateUtil.isOnline)
            taskApiService.createCategory(
                "${NetworkConstants.TOKEN_HEADER} $token",
                category
            ).await()
        else null
    }
}
