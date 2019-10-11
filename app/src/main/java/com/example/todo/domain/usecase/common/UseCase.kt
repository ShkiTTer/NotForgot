package com.example.todo.domain.usecase.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T>: CoroutineScope {
    private val backgroundContext = Dispatchers.IO
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    protected abstract suspend fun doInBackground(): T?

    fun execute(callback: Callback<T>) {
        launch(coroutineContext) {
            try {
                val result = withContext(backgroundContext) {
                    doInBackground()
                }

                callback.onComplete(result)
            }
            catch (t: Throwable) {
                callback.onError(t)
            }
        }
    }

    interface Callback<T> {
        fun onComplete(result: T?)
        fun onError(t: Throwable)
    }
}