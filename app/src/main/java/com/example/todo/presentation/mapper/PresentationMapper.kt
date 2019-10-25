package com.example.todo.presentation.mapper

import com.example.todo.domain.entity.Task
import com.example.todo.presentation.entity.Category
import com.example.todo.presentation.entity.LoginUser
import com.example.todo.presentation.entity.NewUser
import com.example.todo.presentation.interfaces.ListItem

object PresentationMapper {
    private fun categoryToPresentation(category: com.example.todo.domain.entity.Category): Category =
        Category(category.id, category.name)

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

        tasks.sortedBy { it.category.id }.forEach {
            val category = categoryToPresentation(it.category)
            val task = taskToPresentation(it)

            if (!listItems.contains(category)) listItems.add(category)

            listItems.add(task)
        }

        return listItems
    }

    fun categoriesToPresentation(categories: List<com.example.todo.domain.entity.Category>?): List<Category>? =
        categories?.map { categoryToPresentation(it) }

    fun taskToModel(task: com.example.todo.presentation.entity.Task): Task =
        Task(
            task.title,
            task.description,
            if (task.done) 1 else 0,
            task.created,
            task.deadline,
            task.priority!!,
            categoryToModel(task.category!!),
            task.id,
            task.synchronized
        )

    fun categoryToModel(category: Category): com.example.todo.domain.entity.Category =
        com.example.todo.domain.entity.Category(category.id, category.name)

    fun taskToPresentation(task: Task): com.example.todo.presentation.entity.Task =
        com.example.todo.presentation.entity.Task(
            task.title,
            task.description,
            task.created,
            task.synchronized,
            task.id,
            task.deadline,
            categoryToPresentation(task.category),
            task.priority,
            task.done == 1
        )
}