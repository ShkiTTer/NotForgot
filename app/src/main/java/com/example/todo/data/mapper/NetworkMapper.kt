package com.example.todo.data.mapper

import com.example.todo.data.network.entity.RegisterUser
import com.example.todo.data.network.entity.UserToken
import com.example.todo.domain.entity.NewUser

object NetworkMapper {
    fun newUserToNetwork(newUser: NewUser): RegisterUser = RegisterUser(
        newUser.email,
        newUser.name,
        newUser.password
    )

    fun userFromNetwork(userToken: UserToken): com.example.todo.domain.entity.UserToken =
        com.example.todo.domain.entity.UserToken(
            userToken.token
        )
}