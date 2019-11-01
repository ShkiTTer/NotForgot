package com.example.todo.data.db.mapper

import androidx.room.TypeConverter
import java.util.*

object DateConverter {
    @JvmStatic
    @TypeConverter
    fun dateToLong(date: Date?): Long? = date?.time

    @JvmStatic
    @TypeConverter
    fun longToDate(dateLong: Long?): Date? = dateLong?.let { Date(dateLong) }
}