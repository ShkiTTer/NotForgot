package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.User
import com.example.todo.domain.usecase.RegisterUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.NewUser
import com.example.todo.presentation.mapper.PresentationMapper

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    val newUser = ObservableLiveData(NewUser())
    val user = MutableLiveData<User>()

    fun registerUser() {
        val userData = newUser.value ?: return

        registerUseCase.apply {
            newUser = PresentationMapper.newUserFromPresentation(userData)
            execute(object : UseCase.Callback<User> {
                override fun onComplete(result: User?) {
                    user.value = result
                }

                override fun onError(t: Throwable) {
                    user.value = null
                    t.printStackTrace()
                }
            })
        }
    }
}