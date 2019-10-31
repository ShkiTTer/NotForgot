package com.example.todo.domain.usecase

import com.example.todo.domain.repository.IDbRepository
import com.example.todo.domain.repository.ILocalRepository
import com.example.todo.domain.usecase.common.UseCase

class ClearDataUseCase(
    private val localRepository: ILocalRepository,
    private val dbRepository: IDbRepository
) : UseCase<Unit>() {
    override suspend fun doInBackground() {
        localRepository.removeToken()
        dbRepository.clearAll()
    }
}