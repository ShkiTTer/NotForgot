package com.example.todo.presentation.mapper

import com.example.todo.presentation.entity.NewUser

object PresentationMapper {
    fun newUserFromPresentation(newUser: NewUser): com.example.todo.domain.entity.NewUser {
        return com.example.todo.domain.entity.NewUser(
            newUser.email,
            newUser.name,
            newUser.password,
            newUser.confirmPassword
        )
    }
}