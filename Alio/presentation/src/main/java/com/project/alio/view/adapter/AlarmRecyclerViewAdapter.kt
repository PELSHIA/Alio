package com.project.alio.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Alarm
import com.project.alio.databinding.ItemAlarmBinding

class AlarmRecyclerViewAdapter: RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Alarm>()
    private lateinit var onItemClickListener:OnItemClickListener

    interface OnItemClickListener {
        fun onClick(data: Alarm)
    }

    fun setData(list: List<Alarm>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Alarm) -> Unit) {
        onItemClickListener = object :OnItemClickListener {
            override fun onClick(data: Alarm) {
                listener(data)
            }
        }
    }

    inner class ViewHolder(val binding: ItemAlarmBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Alarm) {
            binding.alarm =  data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val binding = ItemAlarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.itemAlarm.setOnClickListener {
            onItemClickListener.onClick(dataList[position])
        }
    }

    override fun getItemCount() = dataList.size
}