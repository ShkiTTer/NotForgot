package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.todo.presentation.interfaces.ListItem

data class Category(
    var id: Int = 0,
    private var _name: String = "",
    var synchronized: Boolean = false
) : BaseObservable(), ListItem {
    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    override fun getItemType(): Int = ListItem.ListType.CATEGORY.ordinal
}