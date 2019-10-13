package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class NewUser(
    private var _email: String = "",
    private var _name: String = "",
    private var _password: String = "",
    private var _confirmPassword: String = ""
) : BaseObservable() {
    var email: String
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    var password: String
        @Bindable get() = _password
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }

    var confirmPassword: String
        @Bindable get() = _confirmPassword
        set(value) {
            _confirmPassword = value
            notifyPropertyChanged(BR.confirmPassword)
        }
}