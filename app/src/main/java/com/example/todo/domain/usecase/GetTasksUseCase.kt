package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class GetTasksUseCase(
    private val networkRepository: INetworkRepository,
    private val dbRepository: IDbRepository
) : UseCase<List<Task>>() {
    var token: String? = null

    override suspend fun doInBackground(): List<Task>? {
        val tempToken = token ?: return null

        val dbData = dbRepository.getTasks().toMutableList()
        val data = mutableListOf<Task>()

        dbData.filter { !it.synchronized }.forEach {
            networkRepository.createTask(tempToken, it)
        }

        val netData = networkRepository.getTasks(tempToken) ?: return dbData

        dbRepository.clearTasks()

        netData.forEach { netTask ->
            dbRepository.addTask(netTask)
            data.add(netTask)
        }

        return data
    }
}
