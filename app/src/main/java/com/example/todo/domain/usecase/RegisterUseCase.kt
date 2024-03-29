package com.example.todo.domain.usecase

import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.UserToken
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class RegisterUseCase(private val networkRepository: INetworkRepository) : UseCase<UserToken>() {
    var newUser: NewUser? = null

    override suspend fun doInBackground(): UserToken? {
        return newUser?.let {
            networkRepository.registerUser(it)
        }
    }
}