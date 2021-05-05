package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_media_player.*

var myIntentService : Intent? = null
class MediaPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        PlayButton.setOnClickListener {
            myIntentService?.setAction(ACTION_PLAY)
            startService(myIntentService)
        }
        StopButton.setOnClickListener {
            myIntentService?.setAction(ACTION_STOP)
            startService(myIntentService)
        }
    }

    override fun onStart() {
        super.onStart()
        if(myIntentService==null){
            myIntentService = Intent(this,AudioPlayerService::class.java)
            myIntentService?.setAction(ACTION_CREATE)
            startService(myIntentService)
        }
    }
}