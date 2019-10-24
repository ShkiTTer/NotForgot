package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.domain.utils.CompareUtil
import java.util.HashSet

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

        if (dbData.isEmpty()) {
            netData.forEach {
                dbRepository.addTask(it)
            }

            return netData
        }

        if (netData.isEmpty()) {
            dbData.forEach {
                dbRepository.deleteTask(it)
            }

            return emptyList()
        }

        if (netData == null) {
            return dbData
        }

        netData.forEach { netTask ->
            val dbTask = dbData.find { it.id == netTask.id }
            dbData.remove(dbTask)

            if (dbTask == null) {
                dbRepository.addTask(netTask)
            }
            else if (CompareUtil.compareTask(netTask, dbTask)) {
                if (netTask.synchronized != dbTask.synchronized)
                {
                    dbTask.synchronized = true
                    dbRepository.updateTask(dbTask)
                }
            }
            else {
                if (!dbTask.synchronized) {
                    networkRepository.updateTask(tempToken, dbTask)
                }
                else dbRepository.updateTask(netTask)
            }

            data.add(netTask)
        }

        dbData.forEach {
            if (!it.synchronized) {
                networkRepository.createTask(tempToken, it)
                data.add(it)
            }
        }

        return data
    }
}