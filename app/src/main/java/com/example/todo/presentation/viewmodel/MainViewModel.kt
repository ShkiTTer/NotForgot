package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.Task
import com.example.todo.domain.usecase.DeleteTaskUseCase
import com.example.todo.domain.usecase.GetTasksUseCase
import com.example.todo.domain.usecase.UpdateTaskUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.interfaces.ListItem
import com.example.todo.presentation.mapper.PresentationMapper

class MainViewModel(
    private val getTasksUseCase: GetTasksUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {
    val taskList = MutableLiveData<List<ListItem>>()
    var token: String? = null
    val tasks = MutableLiveData<List<Task>>()

    init {
        tasks.observeForever {
            if (it == null) {
                taskList.value = null
                return@observeForever
            }

            taskList.value = PresentationMapper.taskListToPresentation(it)
        }
    }

    fun getTasks() {
        getTasksUseCase.apply {
            token = this@MainViewModel.token
            execute(object : UseCase.Callback<List<Task>> {
                override fun onComplete(result: List<Task>?) {
                    tasks.value = result
                }

                override fun onError(t: Throwable) {
                    tasks.value = null
                    t.printStackTrace()
                }
            })
        }
    }

    fun updateTask(position: Int) {
        updateTaskUseCase.apply {
            token = this@MainViewModel.token
            task =
                PresentationMapper.taskToModel(this@MainViewModel.taskList.value?.get(position) as com.example.todo.presentation.entity.Task)
            execute()
        }
    }

    fun deleteTask(position: Int) {
        val task = taskList.value?.toMutableList()?.removeAt(position) as com.example.todo.presentation.entity.Task
        tasks.value = tasks.value?.toMutableList()?.apply {
            remove(this.find { it.id == task.id })
        }

        deleteTaskUseCase.apply {
            token = this@MainViewModel.token
            taskId = task.id
            execute()
        }
    }
}