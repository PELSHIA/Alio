package com.example.data.db.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


@ProvidedTypeConverter
class ArrayListTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun arrayListToJson(value: ArrayList<Boolean>): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToArrayList(value: String): ArrayList<Boolean> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

}