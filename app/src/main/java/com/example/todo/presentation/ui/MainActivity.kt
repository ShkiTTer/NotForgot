package com.example.todo.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.presentation.adapters.TaskListAdapter
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.entity.TaskAction
import com.example.todo.presentation.interfaces.ListItem
import com.example.todo.presentation.interfaces.OnTaskCheckedChangeListener
import com.example.todo.presentation.interfaces.OnTaskClickListener
import com.example.todo.presentation.utils.TaskSwipeToDelete
import com.example.todo.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val taskListAdapter: TaskListAdapter by inject()
    private var synchronizedDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.token = intent.extras?.getString(PresentationConstants.EXTRA_TOKEN)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            lifecycleOwner = this@MainActivity
            tasks = mainViewModel.taskList
        }

        if (mainViewModel.isNetworkEnabled()) {
            synchronizedDialog = createSynchronizedDialog()
            synchronizedDialog?.show()
        }

        setupClickListeners()
        setupRecycler()
        setupListAdapter()
        initObserver()

        binding.refresh.apply {
            setColorSchemeColors(
                getColor(R.color.colorAccent)
            )

            setOnRefreshListener {
                mainViewModel.getTasks()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getTaskList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionLogout -> {
                mainViewModel.clearUserData()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getTaskList() {
        binding.refresh.isRefreshing = true
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

    private fun createSynchronizedDialog(): AlertDialog =
        AlertDialog.Builder(this)
            .setView(R.layout.dialog_synchronize)
            .create()


    private fun initObserver() {
        mainViewModel.taskList.observe(this, Observer {
            taskListAdapter.setItems(it ?: emptyList())
            binding.refresh.isRefreshing = false

            synchronizedDialog?.dismiss()
        })
    }

    private fun setupRecycler() {
        binding.rvTaskList.apply {
            adapter = taskListAdapter
            setHasFixedSize(true)
        }

        val swipeCallback = object : TaskSwipeToDelete(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (binding.rvTaskList.adapter?.getItemViewType(position) != ListItem.ListType.TASK.ordinal) return

                mainViewModel.deleteTask(position)
            }
        }

        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.rvTaskList)
    }

    private fun setupListAdapter() {
        taskListAdapter.setOnTaskClickListener(object : OnTaskClickListener {
            override fun onClick(taskId: Int) {
                val intent = Intent(this@MainActivity, TaskActivity::class.java)

                intent.apply {
                    putExtra(PresentationConstants.EXTRA_TOKEN, mainViewModel.token)
                    putExtra(PresentationConstants.EXTRA_TASK_ACTION, TaskAction.VIEW)
                    putExtra(PresentationConstants.EXTRA_TASK_ID, taskId)
                }

                startActivity(intent)
            }
        })

        taskListAdapter.setOnTaskCheckedChangeListener(object : OnTaskCheckedChangeListener {
            override fun onChange(position: Int) {
                mainViewModel.updateTask(position)
            }
        })
    }
}
