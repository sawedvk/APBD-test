package com.example.apbd

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class MyReceiverAlarm : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notifyID = 12345
        val channel_ID = "Alarm"
        val name = "ON/OFF"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notifyChannel = NotificationChannel(channel_ID,name,importance)
        val nBuilder = NotificationCompat.Builder(context,channel_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("APBD - Lets Plan Now !")
            .setContentText("Remember to stick to your plan !")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        var mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        for(s in mNotificationManager.notificationChannels){
            mNotificationManager.deleteNotificationChannel(s.id)
        }
        mNotificationManager.createNotificationChannel(notifyChannel)
        mNotificationManager.notify(notifyID,nBuilder.build())
    }
}