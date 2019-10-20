package com.example.todo.presentation.databinding

import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.todo.R
import com.example.todo.domain.entity.Priority
import com.example.todo.presentation.adapters.CategoryAdapter
import com.example.todo.presentation.adapters.PriorityAdapter
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
            R.layout.item_spinner,
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
    @InverseBindingAdapter(
        attribute = "app:selectedCategory",
        event = "selectedCategoryAttrChanged"
    )
    fun getSelectedCategory(spinner: Spinner): Category =
        spinner.selectedItem as Category

    @JvmStatic
    @BindingAdapter("selectedCategoryAttrChanged")
    fun bindCategoryChanged(spinner: Spinner, inverseBindingListener: InverseBindingListener) {
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

    @JvmStatic
    @BindingAdapter("app:priorities")
    fun setPriorities(spinner: Spinner, priorities: List<Priority>?) {
        spinner.adapter = PriorityAdapter(spinner.context, R.layout.item_spinner, priorities ?: emptyList())
    }

    @JvmStatic
    @BindingAdapter("app:selectedPriority")
    fun setSelectedPriority(
        spinner: Spinner,
        priority: Priority?
    ) {
        if (priority == null) return

        spinner.setSelection((spinner.adapter as PriorityAdapter).getItemPosition(priority))
    }

    @JvmStatic
    @InverseBindingAdapter(
        attribute = "app:selectedPriority",
        event = "selectedPriorityAttrChanged"
    )
    fun getSelectedPriority(spinner: Spinner): Priority =
        spinner.selectedItem as Priority

    @JvmStatic
    @BindingAdapter("selectedPriorityAttrChanged")
    fun bindPriorityChanged(spinner: Spinner, inverseBindingListener: InverseBindingListener) {
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

    @JvmStatic
    @BindingAdapter("app:color")
    fun setColor(view: View, color: String) {
        view.setBackgroundColor(Color.parseColor(color))
    }
}