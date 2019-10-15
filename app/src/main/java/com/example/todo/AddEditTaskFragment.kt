package com.example.todo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.todo.databinding.FragmentAddEditTaskBinding

class AddEditTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddEditTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_edit_task, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddEditTaskFragment()
    }
}
