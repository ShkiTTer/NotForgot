package com.example.todo.presentation.databinding

import androidx.databinding.BindingAdapter
import com.example.todo.R
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:error")
    fun setError(textInputLayout: TextInputLayout, isError: Boolean) {
        if (isError) {
            textInputLayout.error = textInputLayout.context.getString(R.string.error_empty_field)
        }
        else textInputLayout.error = null
    }
}