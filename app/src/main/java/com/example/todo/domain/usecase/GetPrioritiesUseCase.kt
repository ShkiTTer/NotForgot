package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Priority
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class GetPrioritiesUseCase(private val networkRepository: INetworkRepository): UseCase<List<Priority>>() {
    var token: String? = null

    override suspend fun doInBackground(): List<Priority>? {
        val tempToken = token ?: return null

        return networkRepository.getPriorities(tempToken)
    }
}