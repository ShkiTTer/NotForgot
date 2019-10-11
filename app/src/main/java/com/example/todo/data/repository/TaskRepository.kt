package com.example.todo.data.repository

import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User
import com.example.todo.domain.repository.ITaskRepository

class TaskRepository(private val networkRepository: INetworkRepository): ITaskRepository {
    override fun registerUser(newUser: NewUser): User {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}