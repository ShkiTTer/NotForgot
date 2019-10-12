package com.example.todo.data.mapper

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.User
import com.example.todo.domain.entity.NewUser

object NetworkMapper {
    fun newUserToNetwork(newUser: NewUser): RegisterUser = RegisterUser(
        newUser.email,
        newUser.name,
        newUser.password
    )

    fun userFromNetwork(user: User): com.example.todo.domain.entity.User =
        com.example.todo.domain.entity.User(
            user.id,
            user.email,
            user.name,
            user.token
        )
}