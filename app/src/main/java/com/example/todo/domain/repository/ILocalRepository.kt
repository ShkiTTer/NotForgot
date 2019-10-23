package com.example.todo.domain.repository

interface ILocalRepository {
    fun getToken(): String?
    fun saveToken(token: String?)
    fun removeToken()
}