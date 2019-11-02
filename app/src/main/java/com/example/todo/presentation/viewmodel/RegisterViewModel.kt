package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.UserToken
import com.example.todo.domain.usecase.RegisterUseCase
import com.example.todo.domain.usecase.SaveTokenUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.domain.utils.NetworkStateUtil
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.NewUser
import com.example.todo.presentation.mapper.PresentationMapper
import com.example.todo.presentation.utils.RegistrationFormValidate

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val networkStateUtil: NetworkStateUtil
) : ViewModel() {
    val newUser = ObservableLiveData(NewUser())
    val userToken = MutableLiveData<UserToken>()

    private var isValidated = false

    init {
        newUser.observeForever {
            isValidated = RegistrationFormValidate.validateForm(it)
        }
    }

    fun isOnline(): Boolean = networkStateUtil.isOnline()

    fun registerUser() {
        val userData = newUser.value ?: return
        if (!isValidated) return

        registerUseCase.apply {
            newUser = PresentationMapper.newUserFromPresentation(userData)
            execute(object : UseCase.Callback<UserToken> {
                override fun onComplete(result: UserToken?) {
                    userToken.value = result
                }

                override fun onError(t: Throwable) {
                    userToken.value = null
                    t.printStackTrace()
                }
            })
        }
    }

    fun saveToken() {
        saveTokenUseCase.apply {
            token = userToken.value?.token
            execute()
        }
    }
}