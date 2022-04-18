package com.project.alio.di

import android.app.Application
import com.example.data.db.sharedpreferences.RingtonePreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        RingtonePreferences.init(applicationContext)
    }
}