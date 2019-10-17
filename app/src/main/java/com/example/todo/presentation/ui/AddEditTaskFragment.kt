package com.example.todo.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.FragmentAddEditTaskBinding
import com.example.todo.presentation.viewmodel.AddEditViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddEditTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddEditTaskBinding
    private val addEditViewModel: AddEditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_add_edit_task, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            task = addEditViewModel.task
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddEditTaskFragment()
    }
}
