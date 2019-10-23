package com.example.todo.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    var title: String = "",
    var description: String? = null,
    var created: Date = Date(),
    var deadline: Date? = null,
    @Embedded var category: Category? = null,
    @Embedded var priority: Priority? = null,
    var done: Int = 0,
    var synchronized: Boolean = false,
    @PrimaryKey(autoGenerate = true) var task_id: Int = 0
)