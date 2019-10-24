package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class CreateTaskUseCase(
    private val networkRepository: INetworkRepository,
    private val dbRepository: IDbRepository
) : UseCase<Unit>() {
    var token: String? = null
    var task: Task? = null

    override suspend fun doInBackground() {
        val tempToken = token ?: return
        val tempTask = task ?: return

        networkRepository.createTask(tempToken, tempTask)
        dbRepository.addTask(tempTask)
    }
}