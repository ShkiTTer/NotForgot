package com.example.todo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.Task

class AddEditViewModel: ViewModel() {
    val task = ObservableLiveData(Task())
}