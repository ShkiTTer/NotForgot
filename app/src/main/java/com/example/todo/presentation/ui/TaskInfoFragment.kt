package com.example.todo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.FragmentTaskInfoBinding
import com.example.todo.presentation.entity.TaskAction
import com.example.todo.presentation.viewmodel.TaskInfoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskInfoFragment : Fragment() {

    private val taskInfoViewModel: TaskInfoViewModel by viewModel()
    private lateinit var binding: FragmentTaskInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        taskInfoViewModel.taskId = arguments?.getInt(TASK_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_info, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            task = taskInfoViewModel.task
        }

        setupClickListeners()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        GlobalScope.launch {
            delay(200)
            taskInfoViewModel.getTask()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupClickListeners() {
        binding.btnEdit.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.taskFragmentContainer,
                    AddEditTaskFragment.newInstance(
                        TaskAction.EDIT,
                        arguments?.getString(TOKEN),
                        taskInfoViewModel.taskId
                    )
                )
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    companion object {
        private const val TASK_ID = "task_id"
        private const val TOKEN = "token"

        @JvmStatic
        fun newInstance(taskId: Int?, token: String?) = TaskInfoFragment().apply {
            val args = Bundle().apply {
                if (taskId != null) putInt(TASK_ID, taskId)
                putString(TOKEN, token)
            }

            arguments = args
        }
    }
}
