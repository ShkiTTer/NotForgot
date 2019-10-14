package com.example.todo.data.repository

import android.content.Context
import com.example.todo.Constants.SHARED_PREFERENCES_NAME
import com.example.todo.Constants.SHARED_PREFERENCES_TOKEN
import com.example.todo.data.mapper.NetworkMapper
import com.example.todo.domain.entity.NewUser
import com.example.todo.domain.entity.UserToken
import com.example.todo.domain.repository.ITaskRepository

class TaskRepository(private val networkRepository: INetworkRepository, private val context: Context) : ITaskRepository {
    override suspend fun registerUser(newUser: NewUser): UserToken {
        return NetworkMapper.userFromNetwork(
            networkRepository.registerUser(
                NetworkMapper.newUserToNetwork(
                    newUser
                )
            )
        )
    }

    override suspend fun saveToken(token: String?) {
        val prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        prefs.edit().apply {
            putString(SHARED_PREFERENCES_TOKEN, token)
            apply()
        }
    }

    override suspend fun getToken(): String? {
        val prefs = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        return prefs.getString(SHARED_PREFERENCES_TOKEN, null)
    }
}