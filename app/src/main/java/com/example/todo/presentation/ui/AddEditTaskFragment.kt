package com.example.todo.presentation.ui

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.FragmentAddEditTaskBinding
import com.example.todo.presentation.entity.TaskAction
import com.example.todo.presentation.viewmodel.AddEditViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class AddEditTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddEditTaskBinding
    private val addEditViewModel: AddEditViewModel by viewModel()
    private val applicationContext: Context by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments ?: return

        addEditViewModel.taskAction = args.getSerializable(TASK_ACTION) as TaskAction
        addEditViewModel.token = args.getString(TOKEN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_add_edit_task, container, false
            )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            task = addEditViewModel.task
            categories = addEditViewModel.categories
            priorities = addEditViewModel.priorities
        }

        setupClickListeners()

        addEditViewModel.getCategories()
        addEditViewModel.getPriorities()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.deadline.setOnClickListener {
            createDatePickerDialog().show()
        }

        binding.btnSaveTask.setOnClickListener {
            addEditViewModel.createTask()
        }
    }

    private fun createDatePickerDialog(): DatePickerDialog {
        val calendar = Calendar.getInstance()
        calendar.time = addEditViewModel.task.value?.deadline ?: Date()

        return DatePickerDialog(this.activity!!,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                addEditViewModel.task.value?.deadline = calendar.time
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
    }

    companion object {
        private const val TASK_ACTION = "task_action"
        private const val TOKEN = "token"

        @JvmStatic
        fun newInstance(taskAction: TaskAction, token: String?) = AddEditTaskFragment().apply {
            val args = Bundle().apply {
                putString(TOKEN, token)
                putSerializable(TASK_ACTION, taskAction)
            }

            arguments = args
        }
    }
}
