package com.example.data.db.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.domain.model.RingTone
import com.google.gson.Gson
import java.util.*

@ProvidedTypeConverter
class CalendarTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun calendarToJson(value: Calendar): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToRingtone(value: String): Calendar {
        return gson.fromJson(value, Calendar::class.java)
    }
}