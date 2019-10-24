package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Category
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class CreateCategoryUseCase(
    private val networkRepository: INetworkRepository,
    private val dbRepository: IDbRepository
) : UseCase<Unit>() {
    var token: String? = null
    var category: Category? = null

    override suspend fun doInBackground() {
        val tempToken = token ?: return
        val tempCategory = category ?: return

        val netData = networkRepository.createCategory(tempToken, tempCategory)

        if (netData == null) {
            tempCategory.synchronized = false
        }

        dbRepository.addCategory(tempCategory)
    }
}