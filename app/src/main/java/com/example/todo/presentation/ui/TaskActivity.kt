package com.example.todo.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.R
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.entity.TaskAction

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val action = intent.extras?.get(PresentationConstants.EXTRA_TASK_ACTION) as TaskAction
        val token = intent.extras?.getString(PresentationConstants.EXTRA_TOKEN)
        val taskId = intent.extras?.getInt(PresentationConstants.EXTRA_TASK_ID)

        with(supportFragmentManager.beginTransaction()) {
            when (action) {
                TaskAction.VIEW -> {
                    add(R.id.taskFragmentContainer, TaskInfoFragment.newInstance(taskId))
                }
                TaskAction.ADD -> {
                    add(R.id.taskFragmentContainer, AddEditTaskFragment.newInstance(action, token))
                }
                else -> return
            }

            commit()
        }
    }
}
