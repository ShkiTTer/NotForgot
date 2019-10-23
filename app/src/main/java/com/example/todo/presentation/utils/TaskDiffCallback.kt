package com.example.todo.presentation.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.todo.presentation.entity.Category
import com.example.todo.presentation.entity.Task
import com.example.todo.presentation.interfaces.ListItem

class TaskDiffCallback(private val oldList: List<ListItem>, private val newList: List<ListItem>) :
    DiffUtil.Callback() {
    override fun getNewListSize(): Int = newList.size
    override fun getOldListSize(): Int = oldList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return if (old.getItemType() == new.getItemType()) {
            if (old.getItemType() == ListItem.ListType.TASK.ordinal) (old as Task).id == (new as Task).id
            else (old as Category).id == (new as Category).id
        } else false
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        var old = oldList[oldItemPosition]
        var new = newList[newItemPosition]

        val oldType = old.getItemType()
        val newType = new.getItemType()

        return if (oldType == newType) {
            if (oldType == ListItem.ListType.TASK.ordinal) {
                old = old as Task
                new = new as Task

                old.done == new.done && old.deadline == new.deadline
                        && old.category == new.category
                        && old.priority == new.priority
                        && old.title == new.title
                        && old.description == new.description
            } else {
                old = old as Category
                new = new as Category

                old.name == new.name
            }
        } else false
    }
}