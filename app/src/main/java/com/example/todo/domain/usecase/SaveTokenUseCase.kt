package com.example.todo.domain.usecase

import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.common.UseCase

class SaveTokenUseCase(private val taskRepository: ITaskRepository): UseCase<Unit>() {
    var token: String? = null

    override suspend fun doInBackground() {
        taskRepository.saveToken(token)
    }
}