package com.example.todo.presentation.ui


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.FragmentRegistrationBinding
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding
    private val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!registerViewModel.isOnline()) {
            showError(R.string.message_no_internet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            newUser = registerViewModel.newUser
        }

        setupClickListeners()
        initObservers()

        return binding.root
    }

    private fun showError(@StringRes errorMessage: Int) {
        createErrorDialog(errorMessage).show()
    }

    private fun createErrorDialog(@StringRes errorMessage: Int): AlertDialog =
        AlertDialog.Builder(activity)
            .setTitle(R.string.title_error_dialog)
            .setMessage(errorMessage)
            .setNeutralButton(R.string.neutral_button_error_dialog, null)
            .create()

    private fun initObservers() {
        registerViewModel.userToken.observe(viewLifecycleOwner, Observer {
            println(it)
            if (it != null) {
                registerViewModel.saveToken()

                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra(PresentationConstants.EXTRA_TOKEN, it.token)
                startActivity(intent)

                activity?.finish()
            }
            else {
                showError(R.string.message_registration_error)
            }
        })
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            registerViewModel.registerUser()
        }

        binding.btnLogIn.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }
}
