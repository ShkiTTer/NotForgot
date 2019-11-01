package com.example.todo.data.db.repository

import com.example.todo.data.db.dao.CategoryDao
import com.example.todo.data.db.dao.PriorityDao
import com.example.todo.data.db.dao.TaskDao
import com.example.todo.data.db.mapper.DbMapper
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
        return taskDao.getAllTasks().map { DbMapper.taskToModel(it) }
    }

    override suspend fun getTaskById(taskId: Int): Task {
        return DbMapper.taskToModel(taskDao.getTaskById(taskId))
    }

    override suspend fun getCategories(): List<Category> {
        return categoryDao.getAll().map { DbMapper.categoryToModel(it) }
    }

    override suspend fun getPriorities(): List<Priority> {
        return priorityDao.getAll().map { DbMapper.priorityToModel(it) }
    }

    override suspend fun addTask(task: Task) {
        taskDao.insertTask(DbMapper.taskToDb(task))
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(DbMapper.taskToDb(task))
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(DbMapper.taskToDb(task))
    }

    override suspend fun addCategory(category: Category) {
        categoryDao.add(DbMapper.categoryToDb(category))
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.update(DbMapper.categoryToDb(category))
    }

    override suspend fun clearTasks() {
        taskDao.clear()
    }

    override suspend fun clearCategories() {
        categoryDao.clear()
    }

    override suspend fun clearAll() {
        taskDao.clear()
        categoryDao.clear()
        priorityDao.clear()
    }

    override suspend fun replacePriorities(priorities: List<Priority>) {
        priorityDao.clear()
        priorityDao.addAll(priorities.map { DbMapper.priorityToDb(it) })
    }
}