package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.common.UseCase

class GetTasksUseCase(private val taskRepository: ITaskRepository): UseCase<List<Task>>() {
    var token: String? = null

    override suspend fun doInBackground(): List<Task>? {
        val tempToken = token ?: return null

        return taskRepository.getTasks(tempToken)
    }
}