package com.example.todo.data.db.mapper

import com.example.todo.data.db.entity.Category
import com.example.todo.data.db.entity.Priority
import com.example.todo.data.db.entity.Task

object DbMapper {
    fun taskToModel(task: Task): com.example.todo.domain.entity.Task =
        com.example.todo.domain.entity.Task(
            task.title,
            task.description,
            task.done,
            task.created,
            task.deadline,
            priorityToModel(task.priority),
            categoryToModel(task.category),
            task.task_id,
            task.taskSynchronized
        )

    fun categoryToModel(category: Category): com.example.todo.domain.entity.Category =
        com.example.todo.domain.entity.Category(
            category.categoryId,
            category.categoryName,
            category.categorySynchronized
        )

    fun priorityToModel(priority: Priority): com.example.todo.domain.entity.Priority =
        com.example.todo.domain.entity.Priority(
            priority.priorityId,
            priority.priorityName,
            priority.color
        )

    fun taskToDb(task: com.example.todo.domain.entity.Task): Task =
        Task(
            task.title,
            task.description,
            task.created,
            task.deadline,
            categoryToDb(task.category),
            priorityToDb(task.priority),
            task.done,
            task.synchronized,
            task.id
        )

    fun categoryToDb(category: com.example.todo.domain.entity.Category): Category =
        Category(
            category.id,
            category.name,
            category.synchronized
        )

    fun priorityToDb(priority: com.example.todo.domain.entity.Priority): Priority =
        Priority(
            priority.id,
            priority.name,
            priority.color
        )
}