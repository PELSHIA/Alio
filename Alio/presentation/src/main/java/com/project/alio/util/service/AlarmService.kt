package com.project.alio.util.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.domain.model.Alarm

private const val ACTION_PLAY: String = "com.example.action.PLAY"
class AlarmService: Service(), MediaPlayer.OnPreparedListener {

    private var mMediaPlayer: MediaPlayer? = null

    override fun onPrepared(player: MediaPlayer?) {
        player?.start()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarmData: Alarm = intent?.getSerializableExtra("alarm") as Alarm
        val action: String = intent.action!!
        when (action) {
            ACTION_PLAY -> {
                val uri: Uri = Uri.parse(alarmData.ringtone.uri)
                mMediaPlayer = MediaPlayer().apply {
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                    setDataSource(applicationContext, uri)
                }
                mMediaPlayer!!.setOnPreparedListener(this)
                mMediaPlayer!!.prepareAsync()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.release()
    }
}