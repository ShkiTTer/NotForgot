package com.example.todo.domain.usecase

import com.example.todo.domain.entity.Priority
import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class GetPrioritiesUseCase(
    private val networkRepository: INetworkRepository,
    private val dbRepository: IDbRepository
) : UseCase<List<Priority>>() {
    var token: String? = null

    override suspend fun doInBackground(): List<Priority>? {
        val tempToken = token ?: return null

        val netData = networkRepository.getPriorities(tempToken)
        val dbData = dbRepository.getPriorities()

        println(dbData)

        if (netData == null) {
            return dbData
        }

        if (dbData.isEmpty()) {
            dbRepository.replacePriorities(netData)
        }

        if (netData != dbData) {
            dbRepository.replacePriorities(netData)
        }

        return netData
    }
}