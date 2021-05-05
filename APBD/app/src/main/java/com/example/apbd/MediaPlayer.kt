package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_media_player.*

var IntentMediaService : Intent? = null
class MediaPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        PlayButton.setOnClickListener {
            IntentMediaService?.setAction(ACTION_PLAY)
            startService(IntentMediaService)
        }
        StopButton.setOnClickListener {
            IntentMediaService?.setAction(ACTION_STOP)
            startService(IntentMediaService)
        }
    }

    override fun onStart() {
        super.onStart()
        if(IntentMediaService==null){
            IntentMediaService = Intent(this,AudioPlayerService::class.java)
            IntentMediaService?.setAction(ACTION_CREATE)
            startService(IntentMediaService)
        }
    }
}