package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.todo.domain.entity.Priority
import com.example.todo.presentation.interfaces.ListItem

data class Task(
    private var _title: String = "",
    private var description: String? = null,
    private var created: Int? = null,
    private var deadline: Int? = null,
    private var category: Category = Category(),
    private var priority: Priority
) : BaseObservable(), ListItem {
    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }


    override fun getItemType(): Int = ListItem.ListType.TASK.ordinal
}