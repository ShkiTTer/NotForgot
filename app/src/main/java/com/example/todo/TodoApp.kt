package com.example.todo

import android.app.Application
import com.example.todo.data.network.repository.NetworkRepository
import com.example.todo.data.network.utils.TaskApiProvider
import com.example.todo.data.repository.INetworkRepository
import com.example.todo.data.repository.TaskRepository
import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.GetTokenUseCase
import com.example.todo.domain.usecase.RegisterUseCase
import com.example.todo.domain.usecase.SaveTokenUseCase
import com.example.todo.presentation.viewmodel.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TodoApp : Application() {
    private val koinModule = module {
        single { NetworkRepository(TaskApiProvider.create()) as INetworkRepository }

        single { TaskRepository(get(), androidContext()) as ITaskRepository }

        single { RegisterUseCase(get()) }
        single { GetTokenUseCase(get()) }
        single { SaveTokenUseCase(get()) }

        viewModel { RegisterViewModel(get(), get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(koinModule)
            printLogger()
        }
    }
}