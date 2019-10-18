package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.common.UseCase

class CreateTaskUseCase(private val taskRepository: ITaskRepository): UseCase<Unit>() {
    var token: String? = null
    var task: Task? = null

    override suspend fun doInBackground(): Unit? {
        val tempToken = token ?: return null
        val tempTask = task ?: return null

        return taskRepository.createTask(tempToken, tempTask)
    }
}