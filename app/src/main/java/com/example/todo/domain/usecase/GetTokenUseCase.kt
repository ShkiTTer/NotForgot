package com.example.todo.domain.usecase

import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.common.UseCase

class GetTokenUseCase(private val taskRepository: ITaskRepository): UseCase<String>() {
    override suspend fun doInBackground(): String? {
        return taskRepository.getToken()
    }
}