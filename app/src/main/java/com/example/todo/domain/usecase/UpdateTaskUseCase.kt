package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class UpdateTaskUseCase(private val networkRepository: INetworkRepository): UseCase<Unit>() {
    var task: Task? = null
    var token: String? = null

    override suspend fun doInBackground(): Unit? {
        val tempToken = token ?: return null
        val tempTask = task ?: return null

        return networkRepository.updateTask(tempToken, tempTask)
    }
}