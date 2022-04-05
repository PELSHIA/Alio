package com.project.alio.view.fragment

import android.database.Cursor
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.alio.databinding.FragmentRingtoneBinding

class RingtoneFragment : Fragment() {

    private lateinit var binding: FragmentRingtoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRingtoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRingtoneData()
    }

    private fun initRingtoneData() {
        val manager = RingtoneManager(activity)
        manager.setType(RingtoneManager.TYPE_RINGTONE)
        val cursor: Cursor = manager.cursor
        while (cursor.moveToNext()) {
            val notificationTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val notificationUri: Uri = manager.getRingtoneUri(cursor.position)
        }
    }

    fun listRingtones() {
        val manager = RingtoneManager(activity)
        manager.setType(RingtoneManager.TYPE_RINGTONE)
        val cursor = manager.cursor
        while (cursor.moveToNext()) {
            val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val ringtoneURI = manager.getRingtoneUri(cursor.position)
        }
        cursor.close()
    }

}