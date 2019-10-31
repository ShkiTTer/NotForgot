package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.Category
import com.example.todo.domain.entity.Priority
import com.example.todo.domain.usecase.*
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.Task
import com.example.todo.presentation.entity.TaskAction
import com.example.todo.presentation.mapper.PresentationMapper
import com.example.todo.presentation.utils.TaskFormValidate

class AddEditViewModel(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getPrioritiesUseCase: GetPrioritiesUseCase,
    private val createTaskUseCase: CreateTaskUseCase,
    private val createCategoryUseCase: CreateCategoryUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {
    var token: String? = null
    lateinit var taskAction: TaskAction
    var taskId: Int? = null

    val task = ObservableLiveData(Task())
    val newCategory = ObservableLiveData(com.example.todo.presentation.entity.Category())
    val categories = MutableLiveData<List<com.example.todo.presentation.entity.Category>>()
    val priorities = MutableLiveData<List<Priority>>()

    fun validatedTask(): Boolean = TaskFormValidate.validateTask(task.value ?: Task())

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
        val tempTask = task.value ?: return

        if (validatedTask()) {
            createTaskUseCase.apply {
                token = this@AddEditViewModel.token
                task = PresentationMapper.taskToModel(tempTask)
                execute()
            }
        }
    }

    fun createCategory() {
        createCategoryUseCase.apply {
            token = this@AddEditViewModel.token
            category = PresentationMapper.categoryToModel(this@AddEditViewModel.newCategory.value!!)
            execute()
        }

        newCategory.value = com.example.todo.presentation.entity.Category()
    }

    fun getTask() {
        getTaskByIdUseCase.apply {
            this.taskId = this@AddEditViewModel.taskId
            execute(object : UseCase.Callback<com.example.todo.domain.entity.Task> {
                override fun onComplete(result: com.example.todo.domain.entity.Task?) {
                    if (result == null) {
                        task.value = null
                        return
                    }

                    task.value = PresentationMapper.taskToPresentation(result)
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                    task.value = null
                }
            })
        }
    }

    fun updateTask() {
        val task = this@AddEditViewModel.task.value ?: return

        if (validatedTask()) {
            updateTaskUseCase.apply {
                this.token = this@AddEditViewModel.token
                this.task = PresentationMapper.taskToModel(task)
                execute()
            }
        }
    }
}