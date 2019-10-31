package com.example.todo.domain.utils

import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.Task

object CompareUtil {
    fun compareTask(netTask: Task, dbTask: Task): Boolean =
        netTask.description == dbTask.description
                && netTask.created == dbTask.created
                && netTask.deadline == dbTask.deadline
                && netTask.done == dbTask.done
                && compareCategory(netTask.category, dbTask.category)
                && netTask.priority == dbTask.priority

    fun compareCategory(netCategory: Category, dbCategory: Category): Boolean =
        netCategory.id == dbCategory.id
                && netCategory.name == dbCategory.name

    fun compareTaskId(netTask: Task, dbTask: Task): Boolean =
        netTask.id == dbTask.id
}