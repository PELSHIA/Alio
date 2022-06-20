package com.project.alio.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.project.alio.R
import com.project.alio.databinding.ActivityMainBinding
import com.project.alio.util.PermissionManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permission: PermissionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPermission()
        initNavigation()
        navigateAlarmSetting()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
        binding.bottomNavigationView.setupWithNavController(navController)

    }

    private fun getPermission() {
        permission = PermissionManager(this, this)
        if (!permission.checkPermission()) {
            permission.requestPermission()
        }
    }

    private fun navigateAlarmSetting() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this, AlarmSettingActivity::class.java))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (!permission.permissionResult(requestCode, permissions, grantResults)) {
            permission.requestPermission()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }
}