package com.example.todo.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.todo.domain.entity.Priority

class PriorityAdapter(
    context: Context, @LayoutRes private val layout: Int,
    private val items: List<Priority>
) :
    ArrayAdapter<Priority>(context, layout, items) {

    private val inflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        createView(position, convertView, parent)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        createView(position, convertView, parent)

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView

        if (itemView == null) {
            itemView = inflater.inflate(layout, parent, false)
        }

        (itemView as TextView).text = items[position].name

        return itemView
    }

    fun getItemPosition(item: Priority): Int = items.indexOf(item)
}