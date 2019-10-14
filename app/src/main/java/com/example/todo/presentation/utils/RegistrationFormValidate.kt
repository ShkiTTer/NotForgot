package com.example.todo.presentation.utils

import android.util.Patterns
import com.example.todo.presentation.entity.NewUser

object RegistrationFormValidate {
    fun validatePasswordLength(password: String): Boolean = password.length > 7

    fun validatePasswordConfirmation(password: String, confirmPassword: String): Boolean =
        password.isNotEmpty() && confirmPassword.isNotEmpty() && password == confirmPassword

    fun validateEmail(email: String): Boolean =
        email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validateForm(newUser: NewUser): Boolean =
        newUser.name.isNotEmpty()
                && validatePasswordLength(newUser.password)
                && validateEmail(newUser.email)
                && validatePasswordConfirmation(newUser.password, newUser.confirmPassword)
}