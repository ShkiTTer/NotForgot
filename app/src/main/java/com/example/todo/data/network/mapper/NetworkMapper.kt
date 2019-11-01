package com.example.todo.data.network.mapper

import com.example.todo.data.network.entity.Category
import com.example.todo.data.network.entity.NewTask
import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.Task
import java.util.*

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

    fun newTask(task: Task): NewTask = NewTask(
        task.title,
        task.description,
        task.deadline?.time?.toInt(),
        task.category.id,
        task.priority.id,
        task.done,
        task.id,
        task.created.time
    )

    fun taskListFromNetwork(tasks: List<com.example.todo.data.network.entity.Task>): List<Task> =
        tasks.map {
            Task(
                it.title,
                it.description,
                it.done,
                Date(it.created),
                it.deadline?.let { dateLong -> Date(dateLong) },
                it.priority,
                categoryToModel(it.category),
                it.id
            )
        }

    fun categoryToModel(category: Category): com.example.todo.domain.entity.Category =
        com.example.todo.domain.entity.Category(category.id, category.name)
}