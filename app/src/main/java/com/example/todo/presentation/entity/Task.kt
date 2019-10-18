package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.todo.domain.entity.Priority
import com.example.todo.presentation.interfaces.ListItem

data class Task(
    private var _title: String = "",
    private var _description: String = "",
    var created: Int? = null,
    var deadline: Int? = null,
    private var _category: Category? = null,
    private var _priority: Priority? = null
) : BaseObservable(), ListItem {
    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            notifyPropertyChanged(BR.title)
        }

    var description: String
    @Bindable get() = _description
    set(value) {
        _description = value
        notifyPropertyChanged(BR.description)
    }

    var category: Category?
    @Bindable get() = _category
    set(value) {
        _category = value
        notifyPropertyChanged(BR.category)
    }

    var priority: Priority?
    @Bindable get() = _priority
    set(value) {
        _priority = value
        notifyPropertyChanged(BR.priority)
    }

    override fun getItemType(): Int = ListItem.ListType.TASK.ordinal
}