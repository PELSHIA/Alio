package com.project.alio.util.manager

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionManager(val context: Context, val activity: Activity) {

    private val MULTIPLE_PERMISSIONS = 1000
    private lateinit var permissionList: ArrayList<String>
    private val permissions: ArrayList<String> = arrayListOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    // 거부가 된 권한이 있는지 확인
    fun checkPermission(): Boolean {
        var result: Int
        permissionList = arrayListOf()

        for (pm in permissions) {
            result = ContextCompat.checkSelfPermission(context, pm)
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm)
            }
        }
        if (permissionList.isNotEmpty()) {
            return false
        }
        return true
    }

    // 권한 요청
    fun requestPermission() {
        ActivityCompat.requestPermissions(
            activity, (permissionList.toTypedArray<String?>()), MULTIPLE_PERMISSIONS
        )
    }

    // 권한 결과갑 판단 및 처리
    fun permissionResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean {
        if (requestCode == MULTIPLE_PERMISSIONS && (grantResults.isNotEmpty())) {
            for (result in grantResults) {
                if (result == -1){ // 사용 거부
                    return false
                }
            }
        }
        return true
    }
}