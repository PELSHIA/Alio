package com.example.data.app

import android.app.Application
import com.example.data.db.sharedpreferences.RingtonePreferences

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        RingtonePreferences.init(applicationContext)
    }
}