package com.example.todo.presentation.utils

import com.example.todo.presentation.entity.Task

object TaskFormValidate {
    fun checkTitle(title: String): Boolean =
        title.trim().isNotEmpty()

    fun checkDescription(description: String): Boolean =
        description.trim().isNotEmpty() && description.length <= 120

    fun validateTask(task: Task): Boolean =
        checkTitle(task.title) && checkDescription(task.description)
}