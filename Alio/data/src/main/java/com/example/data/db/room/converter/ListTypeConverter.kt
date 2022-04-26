package com.example.data.db.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


@ProvidedTypeConverter
class ListTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun listToJson(value: List<Boolean>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Boolean> {
        return Gson().fromJson(value, Array<Boolean>::class.java).toList()
    }

}