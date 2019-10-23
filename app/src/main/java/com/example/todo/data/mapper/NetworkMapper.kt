package com.example.todo.data.mapper

import com.example.todo.data.network.entity.NewTask
import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.Task

object NetworkMapper {
    fun newUserToNetwork(newUser: NewUser): RegisterUser = RegisterUser(
        newUser.email,
        newUser.name,
        newUser.password
    )

    fun userTokenFromNetwork(userToken: UserToken): com.example.todo.domain.entity.UserToken =
        com.example.todo.domain.entity.UserToken(
            userToken.token
        )

    fun newTask(task: Task): NewTask = NewTask(task.title, task.description, task.deadline?.time, task.category.id, task.priority.id)
}