package com.example.data.db.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.model.RingTone
import com.google.gson.Gson

object RingtonePreferences {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences("ringtone",  Context.MODE_PRIVATE)
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
    
    var ringtone: RingTone?
        get() {
            val json: String? = preferences.getString("ringtone", null)
            return Gson().fromJson(json, RingTone::class.java)
        }
    set(value) {
        val json: String = Gson().toJson(value)
        preferences.edit().putString("ringtone", json).apply()
    }
}