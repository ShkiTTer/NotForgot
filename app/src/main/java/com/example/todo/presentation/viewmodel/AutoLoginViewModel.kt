package com.example.todo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.usecase.GetTokenUseCase
import com.example.todo.domain.usecase.common.UseCase

class AutoLoginViewModel(private val getTokenUseCase: GetTokenUseCase) : ViewModel() {
    val userToken = MutableLiveData<String>()

    fun getToken() {
        getTokenUseCase.execute(object : UseCase.Callback<String> {
            override fun onComplete(result: String?) {
                userToken.value = result
            }

            override fun onError(t: Throwable) {
                userToken.value = null
                t.printStackTrace()
            }
        })
    }
}