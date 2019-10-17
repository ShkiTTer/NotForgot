package com.example.todo.presentation.databinding

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.todo.presentation.adapters.CategoryAdapter
import com.example.todo.presentation.entity.Category
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:error")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter("app:categories")
    fun setCategories(spinner: Spinner, categories: List<Category>?) {
        spinner.adapter = CategoryAdapter(
            spinner.context,
            android.R.layout.simple_spinner_dropdown_item,
            categories ?: emptyList()
        )
    }

    @JvmStatic
    @BindingAdapter("app:selectedCategory")
    fun setSelectedCategory(
        spinner: Spinner,
        category: Category?
    ) {
        if (category == null) return

        spinner.setSelection((spinner.adapter as CategoryAdapter).getItemPosition(category))
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:selectedCategory", event = "selectedCategoryAttrChanged")
    fun getSelectedCategory(spinner: Spinner): Category =
        spinner.selectedItem as Category

    @JvmStatic
    @BindingAdapter("selectedCategoryAttrChanged")
    fun bindCountryChanged(spinner: Spinner, inverseBindingListener: InverseBindingListener) {
        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                inverseBindingListener.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                inverseBindingListener.onChange()
            }
        }

        spinner.onItemSelectedListener = listener
    }
}