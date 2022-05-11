package com.project.alio.view.adapter

import android.media.Ringtone
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.alio.databinding.ItemRingtoneBinding
import com.example.domain.model.RingTone

class RingtoneRecyclerViewAdapter: RecyclerView.Adapter<RingtoneRecyclerViewAdapter.ViewHolder>() {

    private val dataList = mutableListOf<RingTone>()
    private var selectedPosition: Int = -1
    private var isUpdate: Boolean = false
    private var ringtoneData: RingTone? = null
    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onClick(title: String, uri: String)
    }

    fun setData(list: MutableList<RingTone>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

    fun isUpdate(state: Boolean, ringtone: RingTone?) {
        if (state) {
            isUpdate = true
            ringtoneData = ringtone
        }
    }

    fun setOnItemClickListener(listener: (String, String) -> Unit) {
        onItemClickListener = object : OnItemClickListener {
            override fun onClick(title: String, uri: String) {
                listener(title, uri)
            }
        }
    }

    inner class ViewHolder(val binding: ItemRingtoneBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RingTone) {
            binding.ringtone = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRingtoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.ringtoneSelect.isChecked = position == selectedPosition
        holder.binding.ringtoneSelect.setOnCheckedChangeListener { _, b ->
            if (b) {
                if (dataList[position] == ringtoneData) {
                    holder.binding.ringtoneSelect.isChecked = true
                }
                selectedPosition = holder.adapterPosition
                onItemClickListener.onClick(dataList[position].title, dataList[position].uri)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount() = dataList.size
}