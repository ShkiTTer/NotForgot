package com.example.todo.data.repository

import com.example.todo.data.mapper.NetworkMapper
import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User
import com.example.todo.domain.repository.ITaskRepository

class TaskRepository(private val networkRepository: INetworkRepository) : ITaskRepository {
    override fun registerUser(newUser: NewUser): User {
        return NetworkMapper.userFromNetwork(
            networkRepository.registerUser(
                NetworkMapper.newUserToNetwork(
                    newUser
                )
            )
        )
    }
}