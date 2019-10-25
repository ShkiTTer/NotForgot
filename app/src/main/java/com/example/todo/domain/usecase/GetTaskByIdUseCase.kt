package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.usecase.common.UseCase

class GetTaskByIdUseCase(private val dbRepository: IDbRepository): UseCase<Task>() {
    var taskId: Int? = null

    override suspend fun doInBackground(): Task? {
        val id = taskId ?: return null

        return dbRepository.getTaskById(id)
    }
}