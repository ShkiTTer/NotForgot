package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.Category
import com.example.todo.domain.usecase.GetCategoriesUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.Task
import com.example.todo.presentation.entity.TaskAction

class AddEditViewModel(private val getCategoriesUseCase: GetCategoriesUseCase): ViewModel() {
    var token: String? = null
    lateinit var taskAction: TaskAction
    val task = ObservableLiveData(Task())
    val categories = MutableLiveData<List<Category>>()

    fun getCategories() {
        getCategoriesUseCase.apply {
            token = this@AddEditViewModel.token
            execute(object: UseCase.Callback<List<Category>> {
                override fun onComplete(result: List<Category>?) {
                    categories.value = result
                }

                override fun onError(t: Throwable) {
                    categories.value = null
                    t.printStackTrace()
                }
            })
        }
    }
}