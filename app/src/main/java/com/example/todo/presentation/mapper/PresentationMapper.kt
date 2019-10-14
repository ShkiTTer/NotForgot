package com.example.todo.presentation.mapper

import com.example.todo.presentation.entity.LoginUser
import com.example.todo.presentation.entity.NewUser

object PresentationMapper {
    fun newUserFromPresentation(newUser: NewUser): com.example.todo.domain.entity.NewUser =
        com.example.todo.domain.entity.NewUser(
            newUser.email,
            newUser.name,
            newUser.password,
            newUser.confirmPassword
        )


    fun loginUserFromPresentation(loginUser: LoginUser): com.example.todo.domain.entity.LoginUser =
        com.example.todo.domain.entity.LoginUser(
            loginUser.email,
            loginUser.password
        )
}