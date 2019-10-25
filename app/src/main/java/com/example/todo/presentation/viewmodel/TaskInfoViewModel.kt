package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.Task
import com.example.todo.domain.usecase.GetTaskByIdUseCase
import com.example.todo.domain.usecase.common.UseCase

class TaskInfoViewModel(private val getTaskByIdUseCase: GetTaskByIdUseCase): ViewModel() {
    val task = MutableLiveData<Task>()
    var taskId: Int? = null

    fun getTask() {
        getTaskByIdUseCase.apply {
            this.taskId = this@TaskInfoViewModel.taskId
            execute(object : UseCase.Callback<Task> {
                override fun onComplete(result: Task?) {
                    task.value = result
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                    task.value = null
                }
            })
        }
    }
}