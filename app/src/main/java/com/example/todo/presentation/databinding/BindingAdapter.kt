package com.example.todo.presentation.databinding

import android.graphics.Color
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.todo.R
import com.example.todo.domain.entity.Priority
import com.example.todo.presentation.adapters.CategoryAdapter
import com.example.todo.presentation.adapters.PriorityAdapter
import com.example.todo.presentation.entity.Category
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:error")
    fun setError(textInputLayout: TextInputLayout, error: String?) {
        textInputLayout.error = error
    }

    @JvmStatic
    @BindingAdapter(
        "app:categories",
        "app:selectedCategory",
        "selectedCategoryAttrChanged",
        requireAll = false
    )
    fun setSelectedCategory(
        spinner: Spinner,
        categories: List<Category>?,
        category: Category?,
        listener: InverseBindingListener
    ) {
        if (categories == null) return

        if (spinner.adapter == null)
            spinner.adapter = CategoryAdapter(spinner.context, R.layout.item_spinner, categories)

        setSpinnerListener(spinner, listener)

        if (category == null) return

        setCurrentSelection(spinner, category)
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:selectedCategory")
    fun getSelectedCategory(spinner: Spinner): Category =
        spinner.selectedItem as Category

    @JvmStatic
    @BindingAdapter(
        "app:priorities",
        "app:selectedPriority",
        "selectedPriorityAttrChanged",
        requireAll = false
    )
    fun setSelectedPriority(
        spinner: Spinner,
        priorities: List<Priority>?,
        priority: Priority?,
        listener: InverseBindingListener
    ) {
        if (priorities == null) return

        if (spinner.adapter == null)
            spinner.adapter =
                PriorityAdapter(spinner.context, R.layout.item_spinner, priorities)

        setSpinnerListener(spinner, listener)

        if (priority == null) return

        setCurrentSelection(spinner, priority)
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:selectedPriority")
    fun getSelectedPriority(spinner: Spinner): Priority =
        spinner.selectedItem as Priority


    @JvmStatic
    @BindingAdapter("app:color")
    fun setColor(view: View, color: String?) {
        if (color == null) return
        view.setBackgroundColor(Color.parseColor(color))
    }

    @JvmStatic
    @BindingAdapter("app:date")
    fun setDeadline(view: TextView, date: Date?) {
        val sdf = SimpleDateFormat.getDateInstance(SimpleDateFormat.DATE_FIELD, Locale("ru"))

        if (date == null) view.text = null
        else view.text = sdf.format(date)
    }

    @JvmStatic
    @BindingAdapter("app:done")
    fun setDone(view: TextView, done: Int) {
        if (done == 1) {
            view.setText(R.string.task_done)
            view.setTextColor(view.context.getColor(R.color.colorTaskDone))
        } else {
            view.setText(R.string.task_not_done)
            view.setTextColor(view.context.getColor(R.color.colorTaskNotDone))
        }
    }

    private fun setSpinnerListener(spinner: Spinner, listener: InverseBindingListener) {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) = listener.onChange()

            override fun onNothingSelected(adapterView: AdapterView<*>) = listener.onChange()
        }
    }

    private fun setCurrentSelection(spinner: Spinner, selectedItem: Any): Boolean {
        for (index in 0 until spinner.adapter.count) {
            if (spinner.getItemAtPosition(index) == selectedItem) {
                spinner.setSelection(index)
                return true
            }
        }
        return false
    }
}