package com.example.todo.data.network.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkStateUtil(context: Context) {
    private val service =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isOnline = service.activeNetwork != null
}