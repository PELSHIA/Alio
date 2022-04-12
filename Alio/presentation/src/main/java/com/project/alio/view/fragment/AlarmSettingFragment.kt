package com.project.alio.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.project.alio.R
import com.project.alio.databinding.FragmentAlarmSettingBinding
import com.project.alio.view.activity.AlarmSettingActivity

class AlarmSettingFragment : Fragment() {

    private lateinit var binding: FragmentAlarmSettingBinding

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

        val categoryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, initCategory)
        val missionAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, initMission)

        binding.categorySpinner.adapter = categoryAdapter
        binding.missionSpinner.adapter = missionAdapter
    }

    private fun bindSpinner() {
        binding.categorySpinner.setOnItemClickListener { _, _, position, _ ->
            when(position) {
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
}