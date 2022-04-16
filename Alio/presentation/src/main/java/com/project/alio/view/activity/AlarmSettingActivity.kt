package com.project.alio.view.activity

import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import com.project.alio.R
import com.project.alio.databinding.ActivityAlarmSettingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlarmSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlarmSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlarmSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}