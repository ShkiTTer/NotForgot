package com.example.todo.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ItemCategoryBinding
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.presentation.entity.Category
import com.example.todo.presentation.entity.Task
import com.example.todo.presentation.interfaces.ListItem
import com.example.todo.presentation.interfaces.OnItemClickListener

class TaskListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<ListItem>()

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = items[position].getItemType()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == ListItem.ListType.TASK.ordinal) {
            val binding: ItemTaskBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_task, parent, false)
            TaskViewHolder(binding)
        } else {
            val binding: ItemCategoryBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_category, parent, false)
            CategoryViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ListItem.ListType.TASK.ordinal) {
            (holder as TaskViewHolder).bind(items[position] as Task)
        }
        else {
            (holder as CategoryViewHolder).bind(items[position] as Category)
        }
    }

    fun setItems(newItems: List<ListItem>) {
        items.clear()
        items.addAll(newItems)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onTaskClickListener?.onItemClick(adapterPosition)
            }
        }

        fun bind(task: Task) {
            binding.apply {

            }
        }
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {

            }
        }
    }

    companion object {
        private var onTaskClickListener: OnItemClickListener? = null
    }
}