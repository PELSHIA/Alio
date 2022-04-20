package com.project.alio.view.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.db.sharedpreferences.RingtonePreferences
import com.project.alio.util.receiver.AlarmBroadcastReceiver
import com.example.domain.model.Alarm
import com.project.alio.R
import com.project.alio.databinding.FragmentAlarmSettingBinding
import com.project.alio.view.activity.AlarmSettingActivity
import com.project.alio.viewModel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AlarmSettingFragment : Fragment() {

    private lateinit var binding: FragmentAlarmSettingBinding
    private val viewModel: AlarmViewModel by viewModels()
    private val calendar: Calendar = Calendar.getInstance()
    private lateinit var alarmManager: AlarmManager
    private val isSelect = ContextCompat.getDrawable(
        activity as Context,
        R.drawable.background_week_selected
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmSettingBinding.inflate(inflater, container, false)
        binding.activity = activity as AlarmSettingActivity?
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initPicker()
        initSpinner()
//        bindSpinner()
        setRingtone()
    }

    private fun initView() {
        binding.view.layoutParams.width = binding.alarmSettingTitle.width
    }

    private fun initPicker() {
        binding.alarmHourPicker.minValue = 0
        binding.alarmHourPicker.maxValue = 23
        binding.alarmHourPicker.setFormatter { i -> String.format("%02d", i) }

        binding.alarmMinutePicker.minValue = 0
        binding.alarmMinutePicker.maxValue = 59
        binding.alarmMinutePicker.setFormatter { i -> String.format("%02d", i) }

    }

    private fun initSpinner() {
        val initCategory = resources.getStringArray(R.array.spinner_category)
        val initMission = resources.getStringArray(R.array.spinner_mission)

        val categoryAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            initCategory
        )
        val missionAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            initMission
        )

        binding.categorySpinner.adapter = categoryAdapter
        binding.missionSpinner.adapter = missionAdapter
    }

    private fun bindSpinner() {
        binding.categorySpinner.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                1 -> {
                    TODO("카테고리 추가 다이얼로그 띄우기")
                }
            }
        }
    }

    private fun setRingtone() {
        binding.ringtone.setOnClickListener {
            findNavController().navigate(R.id.action_alarmSettingFragment_to_ringtoneFragment)
        }
    }

    private fun bindCheckButton() {
        binding.alarmSettingCheck.setOnClickListener {
            setAlarmData()
        }
    }

    private fun setAlarmData() {
        setCalendar()
        val alarmName = binding.alarmSettingName.text.toString()
        val category = binding.categorySpinner.selectedItem.toString()
        val mission = binding.missionSpinner.selectedItem.toString()
        val ringtone = RingtonePreferences.ringtone

        viewModel.insertAlarm(
            Alarm(
                UUID.randomUUID().toString(),
                alarmName,
                calendar,
                category,
                mission,
                ringtone!!
            )
        )
        settingAlarm()
    }

    private fun setCalendar() {
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, binding.alarmHourPicker.value)
            set(Calendar.MINUTE, binding.alarmMinutePicker.value)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }
    }

    private fun settingAlarm() {
        val pIntent: PendingIntent = settingIntent()
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pIntent
        )
    }

    private fun settingIntent(): PendingIntent {
        val dayOfWeek: ArrayList<Boolean> = getDayOfWeek()
        val intent: Intent = Intent(activity, AlarmBroadcastReceiver::class.java)
        intent.putExtra("dayOfWeek", dayOfWeek)
        return PendingIntent.getBroadcast(activity, 0, intent, 0)
    }

    private fun getDayOfWeek(): ArrayList<Boolean> {
        return arrayListOf(
            binding.monday.background == isSelect,
            binding.tuesday.background == isSelect,
            binding.wednesday.background == isSelect,
            binding.Thursday.background == isSelect,
            binding.friday.background == isSelect,
            binding.saturday.background == isSelect,
            binding.sunday.background == isSelect
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        RingtonePreferences.clear()
    }
}