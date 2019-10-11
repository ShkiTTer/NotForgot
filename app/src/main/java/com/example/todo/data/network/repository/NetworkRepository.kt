package com.example.todo.data.network.repository

import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User
import com.example.todo.domain.repository.INetworkRepository

class NetworkRepository(private val taskApiService: TaskApiService): INetworkRepository {
    override fun registerUser(newUser: NewUser): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}