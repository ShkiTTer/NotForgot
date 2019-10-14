package com.example.todo.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.todo.R
import com.example.todo.databinding.ActivityLoginBinding
import com.example.todo.presentation.common.PresentationConstants
import com.example.todo.presentation.viewmodel.AutoLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val autoLoginViewModel: AutoLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObserver()
        autoLoginViewModel.getToken()
        setTheme(R.style.AppTheme_NoActionBar)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.apply {
            lifecycleOwner = this@LoginActivity
        }
    }

    private fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fragmentContainer,
                LoginFragment.newInstance()
            )
            .commit()
    }

    private fun openMainActivity(token: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(PresentationConstants.EXTRA_TOKEN, token)
        startActivity(intent)

        finish()
    }

    private fun initObserver() {
        autoLoginViewModel.userToken.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                showLoginFragment()
            }
            else {
                openMainActivity(it)
            }
        })
    }
}
