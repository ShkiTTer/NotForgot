package com.example.todo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.User
import com.example.todo.domain.usecase.RegisterUseCase
import com.example.todo.domain.usecase.common.UseCase

class RegisterViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val mUser = MutableLiveData<User>()

    val user: LiveData<User>
        get() = mUser

    val newUser = MutableLiveData<NewUser>()

    fun registerUser() {
        registerUseCase.apply {
            newUser = this@RegisterViewModel.newUser.value
            execute(object : UseCase.Callback<User> {
                override fun onComplete(result: User?) {
                    mUser.value = result
                }

                override fun onError(t: Throwable) {
                    t.printStackTrace()
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }
    }
}