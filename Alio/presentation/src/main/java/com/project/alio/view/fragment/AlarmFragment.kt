package com.project.alio.view.fragment

import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.domain.model.Alarm
import com.project.alio.R
import com.project.alio.databinding.FragmentAlarmBinding
import com.project.alio.util.service.AlarmService

class AlarmFragment : Fragment() {

    private lateinit var binding: FragmentAlarmBinding
    private val alarmData: Alarm by lazy { activity?.intent!!.getSerializableExtra("alarm")!! as Alarm }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmBinding.inflate(layoutInflater, container, false)
        binding.fragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playRingtone()
        moveToMission()
    }

    private fun playRingtone() {
        val intent: Intent = Intent(activity, AlarmService::class.java)
        intent.putExtra("alarm", alarmData)
        activity?.startService(intent)
    }

    private fun moveToMission() {
        binding.alarmNextButton.setOnClickListener {
            when (alarmData.mission) {
                "할 일 작성" -> {
                    findNavController().navigate(R.id.action_alarmFragment_to_missionTodoListFragment)
                }
                "사진 찍기" -> {
                    findNavController().navigate(R.id.action_alarmFragment_to_missionPictureFragment)
                }
                "명언 따라쓰기" -> {
                    findNavController().navigate(R.id.action_alarmFragment_to_missionQuoteFragment)
                }
            }
        }
    }

}