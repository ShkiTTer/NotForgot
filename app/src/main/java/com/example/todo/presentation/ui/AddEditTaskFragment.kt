package com.example.todo.presentation.ui

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.databinding.DialogInputCategoryBinding
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
        setHasOptionsMenu(true)

        val args = arguments ?: return

        addEditViewModel.taskAction = args.getSerializable(TASK_ACTION) as TaskAction
        addEditViewModel.token = args.getString(TOKEN)
        addEditViewModel.taskId = args.getInt(TASK_ID)
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
            taskAction = addEditViewModel.taskAction
        }

        addEditViewModel.task.observeForever {
            println(it)
        }

        setupClickListeners()

        addEditViewModel.getCategories()
        addEditViewModel.getPriorities()

        if (addEditViewModel.taskAction == TaskAction.EDIT) {
            addEditViewModel.getTask()
        }

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (addEditViewModel.validatedTask()) {
                createSaveDialog()?.show()
            }
            else closeFragment()

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun closeFragment() {
        val manager = fragmentManager ?: return

        if (manager.backStackEntryCount > 0) manager.popBackStack()
        else activity?.finish()
    }

    private fun createSaveDialog(): AlertDialog? {
        val context = this.context ?: return null

        return AlertDialog.Builder(context)
            .setView(R.layout.dialog_save)
            .setPositiveButton(R.string.dialog_save_positive_btn) { _, _ ->
                saveTask()
                closeFragment()
            }
            .setNegativeButton(R.string.dialog_save_negative_btn) { _, _ ->
                closeFragment()
            }
            .create()
    }

    private fun saveTask() {
        Toast.makeText(applicationContext, R.string.success_add_task, Toast.LENGTH_LONG)
            .show()

        if (addEditViewModel.taskAction == TaskAction.EDIT) {
            addEditViewModel.updateTask()
        } else {
            addEditViewModel.createTask()
        }
    }

    private fun setupClickListeners() {
        binding.deadline.setOnClickListener {
            createDatePickerDialog().show()
        }

        binding.btnSaveTask.setOnClickListener {
            saveTask()
            closeFragment()
        }

        binding.ivAddCategory.setOnClickListener {
            createCategoryDialog().show()
        }
    }

    private fun createDatePickerDialog(): DatePickerDialog {
        val calendar = Calendar.getInstance()
        calendar.time = addEditViewModel.task.value?.deadline ?: Date()

        return DatePickerDialog(
            this.activity!!,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                addEditViewModel.task.value?.deadline = calendar.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun createCategoryDialog(): AlertDialog {
        val dialogBinding: DialogInputCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this.activity),
            R.layout.dialog_input_category,
            null,
            false
        )

        dialogBinding.category = addEditViewModel.newCategory

        return AlertDialog.Builder(this.activity)
            .setTitle(R.string.title_add_category)
            .setView(dialogBinding.root)
            .setNegativeButton(R.string.negative_btn, null)
            .setPositiveButton(R.string.positive_btn_add_category) { dialog, which ->
                val name = addEditViewModel.newCategory.value?.name ?: return@setPositiveButton

                if (name.trim().isNotEmpty()) {
                    addEditViewModel.createCategory()

                    Toast.makeText(
                        applicationContext,
                        R.string.success_add_category,
                        Toast.LENGTH_LONG
                    )
                        .show()

                    addEditViewModel.getCategories()
                }
            }.create()
    }

    companion object {
        private const val TASK_ACTION = "task_action"
        private const val TOKEN = "token"
        private const val TASK_ID = "task_id"

        @JvmStatic
        fun newInstance(taskAction: TaskAction, token: String?, taskId: Int? = null) =
            AddEditTaskFragment().apply {
                val args = Bundle().apply {
                    putString(TOKEN, token)
                    putSerializable(TASK_ACTION, taskAction)

                    if (taskId != null) putInt(TASK_ID, taskId)
                }

                arguments = args
            }
    }
}
