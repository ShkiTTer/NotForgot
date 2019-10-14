package com.example.todo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.User
import com.example.todo.domain.usecase.RegisterUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.NewUser
import com.example.todo.presentation.mapper.PresentationMapper
import com.example.todo.presentation.utils.RegistrationFormValidate

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    val newUser = ObservableLiveData(NewUser())
    val user = MutableLiveData<User>()

    private var isValidated = false

    init {
        newUser.observeForever {
            Log.d("D", RegistrationFormValidate.validateForm(it).toString())
            isValidated = RegistrationFormValidate.validateForm(it)
        }
    }

    fun registerUser() {
        val userData = newUser.value ?: return
        if (!isValidated) return

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