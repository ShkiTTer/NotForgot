package com.example.todo.data.db.repository

import com.example.todo.data.db.dao.CategoryDao
import com.example.todo.data.db.dao.PriorityDao
import com.example.todo.data.db.dao.TaskDao

class DbRepository(
    private val taskDao: TaskDao,
    private val categoryDao: CategoryDao,
    private val priorityDao: PriorityDao
) {
}