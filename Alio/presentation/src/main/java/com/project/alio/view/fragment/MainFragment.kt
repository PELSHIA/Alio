package com.project.alio.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.alio.R
import com.project.alio.databinding.FragmentMainBinding
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
        initRecyclerView()
    }

    private fun initView() {
        binding.view.layoutParams.width = binding.alarmListTitle.width
    }

    private fun initRecyclerView() {
        binding.alarmRecyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            recyclerAdapter = AlarmRecyclerViewAdapter()
            adapter = recyclerAdapter
        }
        setRecyclerViewData()
    }

    private fun setRecyclerViewData() {
        viewModel.allAlarmList()
        viewModel.alarmList.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.emptyAlarm.visibility = View.VISIBLE
                binding.alarmRecyclerView.visibility = View.GONE
            } else {
                binding.emptyAlarm.visibility = View.GONE
                binding.alarmRecyclerView.visibility = View.VISIBLE
                recyclerAdapter.setData(it)
            }
        }
    }

}