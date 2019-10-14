package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.Task
import com.example.todo.domain.usecase.GetTasksUseCase
import com.example.todo.domain.usecase.common.UseCase

class MainViewModel(private val getTasksUseCase: GetTasksUseCase) : ViewModel() {
    val tasks = MutableLiveData<List<Task>>()
    var token: String? = null

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
}