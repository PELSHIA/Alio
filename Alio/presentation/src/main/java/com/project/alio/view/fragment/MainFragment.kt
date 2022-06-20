package com.project.alio.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.Alarm
import com.project.alio.databinding.FragmentMainBinding
import com.project.alio.util.manager.SettingAlarmManager
import com.project.alio.util.decorator.RecyclerViewDecoration
import com.project.alio.view.activity.AlarmSettingActivity
import com.project.alio.view.adapter.AlarmRecyclerViewAdapter
import com.project.alio.viewModel.AlarmViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var recyclerAdapter: AlarmRecyclerViewAdapter
    private val viewModel: AlarmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observe()
        initRecyclerView()
        enableBottomSheet()
    }

    private fun initView() {
        binding.view.layoutParams.width = binding.alarmListTitle.width
    }

    private fun initRecyclerView() {
        val decoration: RecyclerViewDecoration = RecyclerViewDecoration(40)
        binding.alarmRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            recyclerAdapter = AlarmRecyclerViewAdapter()
            adapter = recyclerAdapter
            addItemDecoration(decoration)
        }
        setRecyclerViewData()
    }

    private fun setRecyclerViewData() {
        viewModel.allAlarmList()
    }

    private fun enableBottomSheet() {
        recyclerAdapter.setOnItemClickListener {
            val bottomSheet: AlarmBottomSheetFragment = AlarmBottomSheetFragment { state ->
                when (state) {
                    0 -> {
                        updateAlarm(true, it)
                    }
                    1 -> {
                        updateAlarm(false, it)
                    }
                }
            }
            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)
        }
    }

    private fun updateAlarm(state: Boolean, alarm: Alarm) { // Alarm Update of Delete
        when (state) {
            true -> { // Update
                val intent = Intent(activity, AlarmSettingActivity::class.java)
                intent.putExtra("id", alarm.id)
                intent.putExtra("state", true)
                startActivity(intent)
            }
            false -> { // Delete
                SettingAlarmManager().settingAlarm(requireActivity(), alarm.time, alarm, 2)
                viewModel.deleteAlarm(alarm) // Local DB Delete
                recyclerAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun nextAlarm(alarmList: List<Alarm>) {
//        val nextAlarm = alarmManager.nextAlarmClock
//        Log.d("nextAlarm", "$nextAlarm" )
    }

    private fun observe() = with(viewModel) {
        alarmList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.emptyAlarm.visibility = View.VISIBLE
                binding.alarmRecyclerView.visibility = View.GONE
            } else {
                binding.emptyAlarm.visibility = View.GONE
                binding.alarmRecyclerView.visibility = View.VISIBLE
                nextAlarm(it)
                recyclerAdapter.setData(it)
            }
        }
    }
}