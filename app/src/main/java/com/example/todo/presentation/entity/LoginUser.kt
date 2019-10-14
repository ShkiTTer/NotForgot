package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class LoginUser(
    private var _email: String = "",
    private var _password: String = ""
): BaseObservable() {
    var email: String
    @Bindable get() = _email
    set(value) {
        _email = value
        notifyPropertyChanged(BR.email)
    }

    var password: String
    @Bindable get() = _password
    set(value) {
        _password = value
        notifyPropertyChanged(BR.password)
    }
}