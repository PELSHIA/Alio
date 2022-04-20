package com.project.alio.util.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.project.alio.view.activity.AlarmActivity
import java.text.SimpleDateFormat
import java.util.*

class AlarmBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val dayOfWeek = intent!!.getBooleanArrayExtra("dayOfWeek")
        val calendar: Calendar = Calendar.getInstance()
        if (!dayOfWeek?.get(calendar.get(Calendar.DAY_OF_WEEK))!!) return

        val format: SimpleDateFormat = SimpleDateFormat("HH:mm")
        val date: Date = Date()
        val time: String = format.format(date)

        val alarmIntent: Intent = Intent(context, AlarmActivity::class.java)
        alarmIntent.putExtra("setDay", calendar.get(Calendar.DAY_OF_WEEK))
        alarmIntent.putExtra("setTime", time.toString())
        alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(alarmIntent)
    }
}