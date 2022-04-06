package com.project.alio.view.fragment

import android.content.Context
import android.database.Cursor
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.alio.databinding.FragmentRingtoneBinding
import com.project.alio.view.adapter.recyclerView.RingtoneRecyclerViewAdapter
import com.project.alio.view.adapter.recyclerView.data.RingTone
import kotlinx.coroutines.delay

class RingtoneFragment : Fragment() {

    private lateinit var binding: FragmentRingtoneBinding
    private lateinit var recyclerViewAdapter: RingtoneRecyclerViewAdapter
    private lateinit var onItemClickListener: RingtoneRecyclerViewAdapter.OnItemClickListener
    private lateinit var ringtone: Ringtone

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRingtoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() { // Ringtone RecyclerView Setting
        binding.ringtoneRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            recyclerViewAdapter = RingtoneRecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
        val dataList = listRingtones()
        recyclerViewAdapter.setData(dataList)
        recyclerViewAdapter.setOnItemClickListener { s, uri ->
            ringtone = RingtoneManager.getRingtone(activity, uri)
            ringtone.play()
        }
    }

    private fun listRingtones(): MutableList<RingTone> { // get Default Ringtone
        val manager = RingtoneManager(activity)
        manager.setType(RingtoneManager.TYPE_RINGTONE)
        val ringtoneList = mutableListOf<RingTone>()
        val cursor = manager.cursor
        while (cursor.moveToNext()) {
            val title = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX)
            val ringtoneURI = manager.getRingtoneUri(cursor.position)
            ringtoneList.add(RingTone(title, ringtoneURI))
        }
        cursor.close()
        return ringtoneList
    }

    override fun onDestroy() {
        super.onDestroy()
        ringtone.stop()
    }

}