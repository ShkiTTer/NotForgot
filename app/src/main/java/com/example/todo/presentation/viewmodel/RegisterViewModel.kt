package com.example.todo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.User
import com.example.todo.domain.usecase.RegisterUseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.NewUser

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val mUser = MutableLiveData<User>()

    val user: LiveData<User>
        get() = mUser

    val newUser = ObservableLiveData(NewUser())

    fun registerUser() {
        TODO("Have not mapper")
    }
}