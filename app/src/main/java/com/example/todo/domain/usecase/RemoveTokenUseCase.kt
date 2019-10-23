package com.example.todo.domain.usecase

import com.example.todo.domain.repository.ILocalRepository
import com.example.todo.domain.usecase.common.UseCase

class RemoveTokenUseCase(private val localRepository: ILocalRepository): UseCase<Unit>() {
    override suspend fun doInBackground(): Unit? {
        return localRepository.removeToken()
    }
}