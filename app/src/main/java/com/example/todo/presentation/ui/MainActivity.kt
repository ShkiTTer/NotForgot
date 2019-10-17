package com.example.todo.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.entity.TaskAction
import com.example.todo.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val taskListAdapter: TaskListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.token = intent.extras?.getString(PresentationConstants.EXTRA_TOKEN)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@MainActivity
            tasks = mainViewModel.taskList
        }

        setupClickListeners()
        setupRecycler()
        initObserver()

        mainViewModel.getTasks()
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, TaskActivity::class.java).apply {
                putExtra(PresentationConstants.EXTRA_TASK_ACTION, TaskAction.ADD)
                putExtra(PresentationConstants.EXTRA_TOKEN, mainViewModel.token)
            }

            startActivity(intent)
        }
    }

    private fun initObserver() {
        mainViewModel.taskList.observe(this, Observer {
            taskListAdapter.setItems(it ?: emptyList())
        })
    }
    private fun setupRecycler() {
        binding.rvTaskList.apply {
            adapter = taskListAdapter
            setHasFixedSize(true)
        }
    }
}
