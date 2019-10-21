package com.example.todo.domain.usecase

import com.example.todo.domain.repository.ILocalRepository
import com.example.todo.domain.usecase.common.UseCase

class SaveTokenUseCase(private val localRepository: ILocalRepository): UseCase<Unit>() {
    var token: String? = null

    override suspend fun doInBackground() {
        localRepository.saveToken(token)
    }
}