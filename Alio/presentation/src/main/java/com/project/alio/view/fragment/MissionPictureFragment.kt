package com.project.alio.view.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.project.alio.R
import com.project.alio.databinding.FragmentMissionPictureBinding
import java.lang.RuntimeException


class MissionPictureFragment : Fragment() {

    private lateinit var binding: FragmentMissionPictureBinding
    private lateinit var getResult: ActivityResultLauncher<Intent>
    private var state: Boolean = false
    private val cameraPermissionCheck = ContextCompat.checkSelfPermission(
        requireActivity(),
        android.Manifest.permission.CAMERA
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMissionPictureBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLauncher()
        takePicture()
        checkState()
    }

    private fun setLauncher() {
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 1000) {
                val extras: Bundle? = it.data?.extras
                val imageBitmap: Bitmap = extras?.get("data") as Bitmap
                Glide.with(binding.alarmPictureImg.context).load(imageBitmap).into(binding.alarmPictureImg)
                state = true
            }
        }
    }

    private fun takePicture() {
        binding.alarmPictureCamera.setOnClickListener {
            if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) {
                val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                getResult.launch(intent)

            } else {
                Toast.makeText(context, "카메라 권한이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkState() {
        binding.alarmPictureCheck.setOnClickListener {
            if (state) {
                activity?.finish()
            }
        }
    }

}