package com.example.todo.domain.usecase

import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class DeleteTaskUseCase(private val networkRepository: INetworkRepository) : UseCase<Unit>() {
    var token: String? = null
    var taskId: Int? = null

    override suspend fun doInBackground(): Unit? {
        val tempToken = token ?: return null
        val tempId = taskId ?: return null

        return networkRepository.deleteTask(tempToken, tempId)
    }
}