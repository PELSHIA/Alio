package com.example.data.db.room.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.domain.model.RingTone
import com.google.gson.Gson

@ProvidedTypeConverter
class RingtoneTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun ringtoneToJson(value: RingTone): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun jsonToRingtone(value: String): RingTone {
        return gson.fromJson(value, RingTone::class.java)
    }
}