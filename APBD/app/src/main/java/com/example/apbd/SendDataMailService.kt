package com.example.apbd

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class SendDataMailService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Sending...", Toast.LENGTH_SHORT).show()
        Thread(Runnable {
            for (i in 0..10)
                try {
                    Thread.sleep(500L)
                }
                catch (e: Exception){

                }
            stopSelf()
        }).start()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show()
    }
}