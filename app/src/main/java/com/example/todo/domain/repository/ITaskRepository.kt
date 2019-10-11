package com.example.todo.domain.repository

import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User

interface ITaskRepository {
    fun registerUser(newUser: NewUser): User
}