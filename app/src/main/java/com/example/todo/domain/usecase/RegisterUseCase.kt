package com.example.todo.domain.usecase

import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User
import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.common.UseCase

class RegisterUseCase(private val taskRepository: ITaskRepository) : UseCase<User>() {
    var newUser: NewUser? = null

    override suspend fun doInBackground(): User? {
        newUser?.let {
            taskRepository.registerUser(it)
        }

        return null
    }
}