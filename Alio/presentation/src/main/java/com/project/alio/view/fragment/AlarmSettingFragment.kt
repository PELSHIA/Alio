package com.project.alio.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.db.sharedpreferences.RingtonePreferences
import com.example.domain.model.Alarm
import com.project.alio.R
import com.project.alio.databinding.FragmentAlarmSettingBinding
import com.project.alio.util.manager.SettingAlarmManager
import com.project.alio.view.activity.AlarmSettingActivity
import com.project.alio.view.activity.MainActivity
import com.project.alio.viewModel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class AlarmSettingFragment : Fragment() {

    private lateinit var binding: FragmentAlarmSettingBinding
    private val viewModel: AlarmViewModel by viewModels()
    private var alarmId: Long = 0

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
//        bindSpinner()
        observe()
        bindCheckButton()
        setRingtone()
        initData()
    }

    private fun initView() {
        binding.view.layoutParams.width = binding.alarmSettingTitle.width
        initPicker()
        initSpinner()
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

    private fun initData() { // to Update Alarm
        if (requireActivity().intent.hasExtra("state")) {
            viewModel.selectAlarm(requireActivity().intent.getIntExtra("id", 0))
        }
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
            if (requireActivity().intent.hasExtra("state")) {
                updateAlarmData()
            } else {
                when {
                    binding.alarmSettingName.text.isEmpty() -> {
                        Toast.makeText(activity, "알람명을 작성해주세요", Toast.LENGTH_SHORT).show()
                    }
                    RingtonePreferences.ringtone == null -> {
                        Toast.makeText(activity, "밸소리를 세팅해주세요", Toast.LENGTH_SHORT).show()
                    }
                    !isDaySelect() -> {
                        Toast.makeText(activity, "요일을 선택해주세요", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        setAlarmData()
                    }
                }
            }
        }
    }

    private fun setAlarmData() {
        val dayOfWeek = getDayOfWeek()
        val time = setCalendar()
        val alarmName = binding.alarmSettingName.text.toString()
        val category = binding.categorySpinner.selectedItem.toString()
        val mission = binding.missionSpinner.selectedItem.toString()
        val ringtone = RingtonePreferences.ringtone

        viewModel.insertAlarm(
            Alarm(
                0,
                alarmName,
                time,
                dayOfWeek,
                category,
                mission,
                ringtone!!
            )
        )
        SettingAlarmManager().settingAlarm(requireActivity(), time, alarmId.toInt(), dayOfWeek, mission,1)
        activityPopStack()
    }

    private fun updateAlarmData() {
        val id = requireActivity().intent.getIntExtra("id", 0)
        val time = setCalendar()
        val dayOfWeek = getDayOfWeek()
        val alarmName = binding.alarmSettingName.text.toString()
        val category = binding.categorySpinner.selectedItem.toString()
        val mission = binding.missionSpinner.selectedItem.toString()
        val ringtone = RingtonePreferences.ringtone
        viewModel.updateAlarm(
            Alarm(
                id,
                alarmName,
                setCalendar(),
                dayOfWeek,
                category,
                mission,
                ringtone!!
            )
        )
        SettingAlarmManager().settingAlarm(requireActivity(), time, id, dayOfWeek, mission, 3)
        activityPopStack()
    }

    private fun setCalendar(): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, binding.alarmHourPicker.value)
            set(Calendar.MINUTE, binding.alarmMinutePicker.value)
        }
    }

    private fun activityPopStack() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }

    private fun observe() = with(viewModel) {
        alarmId.observe(viewLifecycleOwner) {
            this@AlarmSettingFragment.alarmId = it
        }
        alarm.observe(viewLifecycleOwner) {
            setPickerData(it.time)
            setDayOfWeek(it.dayOfWeek)
            binding.alarmSettingName.setText(it.name)
            setCategoryData(it.category)
            setMissionData(it.mission)
            RingtonePreferences.ringtone = it.ringtone
        }
    }

    private fun setPickerData(time: Calendar) { // to UpdateAlarm Code
        val hour = time.get(Calendar.HOUR_OF_DAY)
        val min = time.get(Calendar.MINUTE)
        binding.alarmHourPicker.value = hour
        binding.alarmMinutePicker.value = min
    }

    private fun setDayOfWeek(day: List<Boolean>) { // to UpdateAlarm Code
        day.forEachIndexed { index, isSelect ->
            when (index) {
                0 -> binding.sunday.isChecked = isSelect
                1 -> binding.monday.isChecked = isSelect
                2 -> binding.tuesday.isChecked = isSelect
                3 -> binding.wednesday.isChecked = isSelect
                4 -> binding.Thursday.isChecked = isSelect
                5 -> binding.friday.isChecked = isSelect
                6 -> binding.saturday.isChecked = isSelect
            }
        }
    }

    private fun setCategoryData(category: String) { // to UpdateAlarm Code

    }

    private fun setMissionData(mission: String) { // to UpdateAlarm Code
        when (mission) {
            "할 일 작성" -> {
                binding.missionSpinner.setSelection(0)
            }
            "사진 찍기" -> {
                binding.missionSpinner.setSelection(1)
            }
            "명언 따라쓰기" -> {
                binding.missionSpinner.setSelection(2)
            }
        }
    }

    private fun getDayOfWeek(): List<Boolean> {
        return listOf(
            binding.sunday.isChecked,
            binding.monday.isChecked,
            binding.tuesday.isChecked,
            binding.wednesday.isChecked,
            binding.Thursday.isChecked,
            binding.friday.isChecked,
            binding.saturday.isChecked
        )
    }

    private fun isDaySelect(): Boolean {
        return binding.sunday.isChecked ||
        binding.monday.isChecked ||
        binding.tuesday.isChecked ||
        binding.wednesday.isChecked ||
        binding.Thursday.isChecked ||
        binding.friday.isChecked ||
        binding.saturday.isChecked
    }

    override fun onDestroy() {
        super.onDestroy()
        RingtonePreferences.clear()
    }
}