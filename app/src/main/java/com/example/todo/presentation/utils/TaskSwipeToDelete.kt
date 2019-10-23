package com.example.todo.presentation.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.presentation.interfaces.ListItem

abstract class TaskSwipeToDelete(context: Context) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val color = context.getColor(R.color.colorDelete)
    private val icon = context.getDrawable(R.drawable.ic_delete)!!
    private val intrinsicWidth = icon.intrinsicWidth
    private val intrinsicHeight = icon.intrinsicHeight

    private val background = ColorDrawable(color)

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return if (recyclerView.adapter?.getItemViewType(viewHolder.adapterPosition) == ListItem.ListType.CATEGORY.ordinal) 0
        else super.getSwipeDirs(recyclerView, viewHolder)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val view = viewHolder.itemView
        val itemHeight = view.height

        background.setBounds(view.right + dX.toInt(), view.top, view.right, view.bottom)
        background.draw(c)

        val iconMargin = (itemHeight - intrinsicHeight) / 2
        val iconTop = view.top + iconMargin
        val iconBottom = iconTop + intrinsicHeight
        val iconLeft = view.right - iconMargin - intrinsicWidth
        val iconRight = view.right - iconMargin

        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
        icon.draw(c)
    }
}