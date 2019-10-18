package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.Priority
import com.example.todo.domain.usecase.CreateTaskUseCase
import com.example.todo.domain.usecase.GetCategoriesUseCase
import com.example.todo.domain.usecase.GetPrioritiesUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.Task
import com.example.todo.presentation.entity.TaskAction
import com.example.todo.presentation.mapper.PresentationMapper

class AddEditViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getPrioritiesUseCase: GetPrioritiesUseCase,
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    var token: String? = null
    lateinit var taskAction: TaskAction

    val task = ObservableLiveData(Task())
    val categories = MutableLiveData<List<com.example.todo.presentation.entity.Category>>()
    val priorities = MutableLiveData<List<Priority>>()

    fun getCategories() {
        getCategoriesUseCase.apply {
            token = this@AddEditViewModel.token
            execute(object : UseCase.Callback<List<Category>> {
                override fun onComplete(result: List<Category>?) {
                    categories.value = PresentationMapper.categoriesToPresentation(result)
                }

                override fun onError(t: Throwable) {
                    categories.value = null
                    t.printStackTrace()
                }
            })
        }
    }

    fun getPriorities() {
        getPrioritiesUseCase.apply {
            token = this@AddEditViewModel.token
            execute(object : UseCase.Callback<List<Priority>> {
                override fun onComplete(result: List<Priority>?) {
                    priorities.value = result
                }

                override fun onError(t: Throwable) {
                    priorities.value = null
                    t.printStackTrace()
                }
            })
        }
    }

    fun createTask() {
        createTaskUseCase.apply {
            token = this@AddEditViewModel.token
            task = PresentationMapper.taskToModel(this@AddEditViewModel.task.value!!)
            execute(object : UseCase.Callback<Unit> {
                override fun onComplete(result: Unit?) {
                    if (result == null) {
                        println("Error")
                    }
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }
}