package com.project.alio.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Alarm
import com.project.alio.databinding.ItemAlarmBinding

class AlarmRecyclerViewAdapter: RecyclerView.Adapter<AlarmRecyclerViewAdapter.ViewHolder>() {

    private val dataList = mutableListOf<Alarm>()

    fun setData(list: List<Alarm>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemAlarmBinding): RecyclerView.ViewHolder(binding.root) {
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
    }

    override fun getItemCount() = dataList.size
}