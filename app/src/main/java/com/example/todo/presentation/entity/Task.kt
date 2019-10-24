package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.todo.domain.entity.Priority
import com.example.todo.presentation.interfaces.ListItem
import java.util.*

data class Task(
    private var _title: String = "",
    private var _description: String = "",
    var created: Date = Date(),
    var synchronized: Boolean = false,
    val id: Int = 0,
    private var _deadline: Date? = null,
    private var _category: Category? = null,
    private var _priority: Priority? = null,
    private var _done: Boolean = false
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

    var done: Boolean
    @Bindable get() = _done
    set(value) {
        _done = value
        notifyPropertyChanged(BR.done)
    }

    var deadline: Date?
    @Bindable get() = _deadline
    set(value) {
        _deadline = value
        notifyPropertyChanged(BR.deadline)
    }

    override fun getItemType(): Int = ListItem.ListType.TASK.ordinal
}