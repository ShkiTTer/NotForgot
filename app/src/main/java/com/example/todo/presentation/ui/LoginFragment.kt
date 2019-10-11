package com.example.todo.presentation.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todo.R
import com.example.todo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner

            btnRegistration.setOnClickListener {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, RegistrationFragment.newInstance())
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }

        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
