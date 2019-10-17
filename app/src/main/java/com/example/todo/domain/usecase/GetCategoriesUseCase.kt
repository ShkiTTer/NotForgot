package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Category
import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.common.UseCase

class GetCategoriesUseCase(private val taskRepository: ITaskRepository): UseCase<List<Category>>() {
    var token: String? = null

    override suspend fun doInBackground(): List<Category>? {
        val tempToken = token ?: return null

        return taskRepository.getCategories(tempToken)
    }
}