package com.example.todo.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.ItemCategoryBinding
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.presentation.entity.Category
import com.example.todo.presentation.entity.Task
import com.example.todo.presentation.interfaces.ListItem
import com.example.todo.presentation.interfaces.OnTaskClickListener
import com.example.todo.presentation.interfaces.OnTaskCheckedChangeListener
import com.example.todo.presentation.utils.TaskDiffCallback
import kotlinx.android.synthetic.main.item_task.view.*

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
        val diffCallback = TaskDiffCallback(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(newItems)

        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnTaskClickListener(listener: OnTaskClickListener) {
        onTaskClickListener = listener
    }

    fun setOnTaskCheckedChangeListener(listener: OnTaskCheckedChangeListener) {
        onTaskCheckedChangeListener = listener
    }

    private inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.taskDone.setOnClickListener {
                onTaskCheckedChangeListener?.onChange(adapterPosition)
            }

            binding.root.setOnClickListener {
                onTaskClickListener?.onClick((items[adapterPosition] as Task).id)
            }
        }

        fun bind(task: Task) {
            binding.apply {
                this.task = task
            }
        }
    }

    private inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.apply {
                this.category = category
            }
        }
    }

    companion object {
        private var onTaskClickListener: OnTaskClickListener? = null
        private var onTaskCheckedChangeListener: OnTaskCheckedChangeListener? = null
    }
}