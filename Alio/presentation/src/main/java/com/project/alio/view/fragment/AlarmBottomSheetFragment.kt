package com.project.alio.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.project.alio.R
import com.project.alio.databinding.FragmentAlarmBottomSheetBinding

class AlarmBottomSheetFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {

    private val binding: FragmentAlarmBottomSheetBinding by lazy { FragmentAlarmBottomSheetBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alarmBsUpdate.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }

        binding.alarmBsDelete.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
    }

}