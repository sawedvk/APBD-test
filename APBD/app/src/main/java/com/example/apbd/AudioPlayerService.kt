package com.example.apbd

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.MediaStore
import android.widget.Toast

const val ACTION_PLAY = "PLAY"
const val ACTION_STOP = "STOP"
const val ACTION_CREATE = "CREATE"

class AudioPlayerService : Service(),
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {

    private var AudioPlayer: MediaPlayer? = null
    fun init() {
        AudioPlayer = MediaPlayer()
        AudioPlayer?.setOnPreparedListener(this)
        AudioPlayer?.setOnCompletionListener(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null) {
            var actionIntent = intent.action
            when (actionIntent) {
                ACTION_CREATE -> init()
                ACTION_PLAY -> {
                    if (!AudioPlayer!!.isPlaying) {
                        AudioPlayer?.run {
                            reset()
                            setDataSource("https://pl.meln.top/mr/2646b2209b0d46a2ba8d6ae033bef29f.mp3?session_key=cdd150fee4c52e2dd773e8279219d523")
                            prepareAsync()
                        }
                    }
                }
                ACTION_STOP -> AudioPlayer?.stop()
            }
        }
        return flags
    }

    override fun onPrepared(p0: MediaPlayer?) {
        AudioPlayer?.start()
    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        Toast.makeText(this, "Error Read File", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onCompletion(p0: MediaPlayer?) {
        Toast.makeText(this, "Player Stop", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        AudioPlayer?.release()
    }
}