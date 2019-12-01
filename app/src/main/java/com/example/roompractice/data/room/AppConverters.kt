package com.example.roompractice.data.room

import androidx.room.TypeConverter
import java.util.*

object AppConverters {
    @JvmStatic
    @TypeConverter
    fun fromTimestamp(value: Long): Date = Date(value)

    @JvmStatic
    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time
}