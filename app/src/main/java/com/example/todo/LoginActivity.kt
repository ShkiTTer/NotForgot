package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.todo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.apply {
            lifecycleOwner = this@LoginActivity
        }

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, LoginFragment.newInstance())
            .commit()
    }
}
