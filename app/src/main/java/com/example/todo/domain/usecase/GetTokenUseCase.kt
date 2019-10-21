package com.example.todo.domain.usecase

import com.example.todo.domain.repository.ILocalRepository
import com.example.todo.domain.usecase.common.UseCase

class GetTokenUseCase(private val localRepository: ILocalRepository) : UseCase<String>() {
    override suspend fun doInBackground(): String? {
        return localRepository.getToken()
    }
}