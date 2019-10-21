package com.example.todo

import android.app.Application
import androidx.room.Room
import com.example.todo.data.db.common.AppDatabase
import com.example.todo.data.db.repository.DbRepository
import com.example.todo.data.network.repository.NetworkRepository
import com.example.todo.data.network.utils.TaskApiProvider
import com.example.todo.data.repository.INetworkRepository
import com.example.todo.data.repository.TaskRepository
import com.example.todo.domain.repository.ITaskRepository
import com.example.todo.domain.usecase.*
import com.example.todo.presentation.adapters.TaskListAdapter
import com.example.todo.presentation.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TodoApp : Application() {
    private val koinModule = module {
        single { NetworkRepository(TaskApiProvider.create()) as INetworkRepository }
        single {
            Room.databaseBuilder(androidContext(), AppDatabase::class.java, "todo_db").build()
        }
        single {
            DbRepository(
                get<AppDatabase>().getTaskDao(),
                get<AppDatabase>().getCategoryDao(),
                get<AppDatabase>().getPriorityDao()
            )
        }
        single { TaskRepository(get(), androidContext()) as ITaskRepository }

        single { RegisterUseCase(get()) }
        single { LoginUseCase(get()) }
        single { GetTokenUseCase(get()) }
        single { SaveTokenUseCase(get()) }
        single { GetTasksUseCase(get()) }
        single { GetCategoriesUseCase(get()) }
        single { GetPrioritiesUseCase(get()) }
        single { CreateTaskUseCase(get()) }

        single { TaskListAdapter() }

        viewModel { RegisterViewModel(get(), get()) }
        viewModel { LoginViewModel(get(), get()) }
        viewModel { AutoLoginViewModel(get()) }
        viewModel { MainViewModel(get()) }
        viewModel { AddEditViewModel(get(), get(), get()) }
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