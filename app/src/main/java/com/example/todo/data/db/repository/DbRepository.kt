package com.example.todo.data.db.repository

import com.example.todo.data.db.dao.CategoryDao
import com.example.todo.data.db.dao.PriorityDao
import com.example.todo.data.db.dao.TaskDao
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.Priority
import com.example.todo.domain.entity.Task
import com.example.todo.domain.repository.IDbRepository

class DbRepository(
    private val taskDao: TaskDao,
    private val categoryDao: CategoryDao,
    private val priorityDao: PriorityDao
): IDbRepository {
    override suspend fun getTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getCategories(): List<Category> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getPriorities(): List<Priority> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun updateTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun deleteTask(task: Task) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addCategory(category: Category) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}