package com.example.apbd

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast

const val ACTION_PLAY = "PLAY"
const val ACTION_STOP = "STOP"
const val ACTION_CREATE = "CREATE"

class AudioPlayerService : Service(),
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener {

    private var myMediaPlayer: MediaPlayer? = null
    fun init() {
        myMediaPlayer = MediaPlayer()
        myMediaPlayer?.setOnPreparedListener(this)
        myMediaPlayer?.setOnCompletionListener(this)
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
                    if (!myMediaPlayer!!.isPlaying) {
                        myMediaPlayer?.run {
                            reset()
                            setDataSource("https://fileserver.louisaldorio.site/audio/音闕詩聽–芒種-MangZhong-120900498.mp3")
                            prepareAsync()
                        }
                    }
                }
                ACTION_STOP -> myMediaPlayer?.stop()
            }
        }
        return flags
    }

    override fun onPrepared(p0: MediaPlayer?) {
        myMediaPlayer?.start()
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
        myMediaPlayer?.release()
    }
}