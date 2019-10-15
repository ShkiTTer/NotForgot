package com.example.todo.presentation.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.todo.presentation.interfaces.ListItem

data class Category(
    private var _name: String = ""
) : BaseObservable(), ListItem {
    var name: String
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    override fun getItemType(): Int = ListItem.ListType.CATEGORY.ordinal
}