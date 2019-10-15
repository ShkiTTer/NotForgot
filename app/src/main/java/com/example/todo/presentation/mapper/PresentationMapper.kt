package com.example.todo.presentation.mapper

import com.example.todo.domain.entity.Task
import com.example.todo.presentation.entity.Category
import com.example.todo.presentation.entity.LoginUser
import com.example.todo.presentation.entity.NewUser
import com.example.todo.presentation.interfaces.ListItem

object PresentationMapper {
    fun newUserFromPresentation(newUser: NewUser): com.example.todo.domain.entity.NewUser =
        com.example.todo.domain.entity.NewUser(
            newUser.email,
            newUser.name,
            newUser.password,
            newUser.confirmPassword
        )

    fun loginUserFromPresentation(loginUser: LoginUser): com.example.todo.domain.entity.LoginUser =
        com.example.todo.domain.entity.LoginUser(
            loginUser.email,
            loginUser.password
        )

    fun taskListToPresentation(tasks: List<Task>): List<ListItem> {
        val listItems = mutableListOf<ListItem>()

        tasks.sortedBy { it.category.name }.forEach {
            val category = Category(it.category.id, it.category.name)
            val task = com.example.todo.presentation.entity.Task(
                it.title,
                it.description,
                it.created,
                it.deadline,
                category,
                it.priority

            )

            if (!listItems.contains(category)) listItems.add(category)

            listItems.add(task)
        }

        return listItems
    }
}