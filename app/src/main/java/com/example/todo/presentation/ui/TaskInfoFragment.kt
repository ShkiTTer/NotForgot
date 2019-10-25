package com.example.todo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.FragmentTaskInfoBinding
import com.example.todo.presentation.viewmodel.TaskInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskInfoFragment : Fragment() {

    private val taskInfoViewModel: TaskInfoViewModel by viewModel()
    private lateinit var binding: FragmentTaskInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        taskInfoViewModel.getTask()
    }


    companion object {
        private const val TASK_ID = "task_id"

        @JvmStatic
        fun newInstance(taskId: Int?) = TaskInfoFragment().apply {
            val args = Bundle().apply {
                putInt(TASK_ID, taskId!!)
            }

            arguments = args
        }
    }
}
