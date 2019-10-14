package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.UserToken
import com.example.todo.domain.usecase.LoginUseCase
import com.example.todo.domain.usecase.SaveTokenUseCase
import com.example.todo.domain.usecase.common.UseCase
import com.example.todo.presentation.common.ObservableLiveData
import com.example.todo.presentation.entity.LoginUser
import com.example.todo.presentation.mapper.PresentationMapper

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {
    val userToken = MutableLiveData<UserToken>()
    val loginUser = ObservableLiveData(LoginUser())

    fun login() {
        val userData = loginUser.value ?: return

        loginUseCase.apply {
            loginUser = PresentationMapper.loginUserFromPresentation(userData)
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
            token = this@LoginViewModel.userToken.value?.token
            execute()
        }
    }
}