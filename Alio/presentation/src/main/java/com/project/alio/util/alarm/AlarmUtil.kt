package com.project.alio.util.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.project.alio.util.receiver.AlarmBroadcastReceiver
import java.util.*
import kotlin.collections.ArrayList

class AlarmUtil {
    private lateinit var manager: AlarmManager

    private fun listToArrayList(list: List<Boolean>): ArrayList<Boolean> {
        val arrayList: ArrayList<Boolean> = arrayListOf()
        arrayList.addAll(list)
        return arrayList
    }

    fun settingAlarm(context: Context, time: Calendar, alarmId: Int, dayOfWeek: List<Boolean>, state: Int) {
        manager = context.applicationContext?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val day: ArrayList<Boolean> = listToArrayList(dayOfWeek)
        val intent = Intent(context, AlarmBroadcastReceiver::class.java)
        intent.putExtra("dayOfWeek", day)
        val pIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0)
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