package com.project.alio.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import com.project.alio.R
import com.project.alio.databinding.ActivityAlarmSettingBinding


class AlarmSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmSettingBinding.inflate(layoutInflater)
        binding.activity = this
        setContentView(binding.root)

        initPicker()
    }

    private fun initPicker() {
        binding.alarmHourPicker.minValue = 0
        binding.alarmHourPicker.maxValue = 23
        binding.alarmHourPicker.setFormatter { i -> String.format("%02d", i) }

        binding.alarmMinutePicker.minValue = 0
        binding.alarmMinutePicker.maxValue = 59
        binding.alarmMinutePicker.setFormatter { i -> String.format("%02d", i) }

    }
}