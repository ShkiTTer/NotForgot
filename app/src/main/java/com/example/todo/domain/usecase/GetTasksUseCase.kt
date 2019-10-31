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

        val netData = networkRepository.getTasks(tempToken)
        val dbData = dbRepository.getTasks().toMutableList()
        val data = mutableListOf<Task>()

        if (netData == null) {
            return dbData
        }

        dbRepository.clearTasks()

        if (dbData.isEmpty()) {
            netData.forEach {
                dbRepository.addTask(it)
            }

            return netData
        }

        data.addAll(netData)

        netData.forEach { netTask ->
            dbRepository.addTask(netTask)
        }

        /*dbData.filter { !it.synchronized }.forEach {
            networkRepository.createTask(tempToken, it)
            data.add(it)
        }*/

        return data
    }
}