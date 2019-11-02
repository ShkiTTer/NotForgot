package com.example.todo.presentation.ui


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.FragmentLoginBinding
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!loginViewModel.isOnline()) {
            showError(R.string.message_no_internet)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            loginUser = loginViewModel.loginUser
        }

        setupClickListeners()
        initObservers()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.btnRegistration.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, RegistrationFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()
        }

        binding.btnLogin.setOnClickListener {
            loginViewModel.login()
        }
    }

    private fun initObservers() {
        loginViewModel.userToken.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                loginViewModel.saveToken()

                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra(PresentationConstants.EXTRA_TOKEN, it.token)
                startActivity(intent)

                activity?.finish()
            }
            else {
                showError(R.string.message_login_error)
            }
        })
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


    companion object {

        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}
