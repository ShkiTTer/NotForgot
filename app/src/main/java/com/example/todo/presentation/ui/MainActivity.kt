package com.example.todo.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.token = intent.extras?.getString(PresentationConstants.EXTRA_TOKEN)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@MainActivity
            tasks = mainViewModel.tasks
        }

        setupClickListeners()
        initObserver()

        mainViewModel.getTasks()
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            TODO("Add activity for add or edit task")
        }
    }

    private fun initObserver() {
        mainViewModel.tasks.observe(this, Observer {
            println(it)
        })
    }
}
