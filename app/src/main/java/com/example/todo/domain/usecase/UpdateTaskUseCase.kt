package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class UpdateTaskUseCase(
    private val networkRepository: INetworkRepository,
    private val dbRepository: IDbRepository
) : UseCase<Unit>() {
    var task: Task? = null
    var token: String? = null

    override suspend fun doInBackground() {
        val tempToken = token ?: return
        val tempTask = task ?: return

        networkRepository.updateTask(tempToken, tempTask)
        dbRepository.updateTask(tempTask)
    }
}