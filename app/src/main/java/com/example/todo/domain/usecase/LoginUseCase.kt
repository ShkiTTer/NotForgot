package com.example.todo.domain.usecase

import com.example.todo.domain.entity.LoginUser
import com.example.todo.domain.entity.UserToken
import com.example.todo.domain.repository.INetworkRepository
import com.example.todo.domain.usecase.common.UseCase

class LoginUseCase(private val networkRepository: INetworkRepository): UseCase<UserToken>() {
    var loginUser: LoginUser? = null

    override suspend fun doInBackground(): UserToken? {
        val user = loginUser ?: return null

        return networkRepository.login(user)
    }
}