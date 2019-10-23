package com.example.todo.data.local

import android.content.Context
import com.example.todo.domain.repository.ILocalRepository

class LocalRepository(context: Context) : ILocalRepository {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getToken(): String? =
        prefs.getString(PREFS_TOKEN, null)


    override fun saveToken(token: String?) {
        prefs.edit().apply {
            putString(PREFS_TOKEN, token)
            apply()
        }
    }

    override fun removeToken() {
        prefs.edit().apply{
            clear()
            apply()
        }
    }

    companion object {
        private const val PREFS_NAME = "todo_prefs"
        private const val PREFS_TOKEN = "todo_token"
    }
}