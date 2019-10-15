package com.example.todo.presentation.interfaces

interface ListItem {
    enum class ListType(val value: Int) {
        CATEGORY(0), TASK(1)
    }

    fun getItemType(): Int
}