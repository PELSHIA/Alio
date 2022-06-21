package com.project.alio.util.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.domain.model.Alarm
import com.project.alio.view.activity.AlarmActivity
import java.text.SimpleDateFormat
import java.util.*

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmData = intent?.getSerializableExtra("alarm") as Alarm
        val calendar: Calendar = Calendar.getInstance()
        if (!alarmData.dayOfWeek[calendar.get(Calendar.DAY_OF_WEEK)]) {
            return
        } else {
            val alarmIntent: Intent = Intent(context, AlarmActivity::class.java)
            alarmIntent.putExtra("setDay", calendar.get(Calendar.DAY_OF_WEEK))
            alarmIntent.putExtra("alarm", alarmData)
            alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context?.startActivity(alarmIntent)
        }
    }
}