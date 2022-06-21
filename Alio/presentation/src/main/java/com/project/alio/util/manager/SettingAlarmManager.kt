package com.project.alio.util.manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.domain.model.Alarm
import com.project.alio.util.receiver.AlarmBroadcastReceiver
import java.util.*
import kotlin.collections.ArrayList

class SettingAlarmManager {
    private lateinit var manager: AlarmManager

    fun settingAlarm(context: Context, time: Calendar, alarm: Alarm, state: Int) {
        manager = context.applicationContext?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra("alarm", alarm)
        val pIntent = PendingIntent.getBroadcast(context, alarm.id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        when (state) {
            1 -> startAlarm(pIntent, time)
            2 -> cancelAlarm(pIntent)
            3 -> {
                cancelAlarm(pIntent)
                startAlarm(pIntent, time)
            }
        }
    }

    private fun startAlarm(pIntent: PendingIntent, time: Calendar) {
        manager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            time.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pIntent
        )
    }

    private fun cancelAlarm(pIntent: PendingIntent) {
        manager.cancel(pIntent)
    }
}