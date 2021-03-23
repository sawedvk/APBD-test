package com.example.apbd

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class AirplaneReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Settings.System.getInt(context.contentResolver
                        ,Settings.Global.AIRPLANE_MODE_ON,0)==0){
            Toast.makeText(context,"Airplane Mode OFF",Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(context,"Airplane Mode ON",Toast.LENGTH_SHORT).show()
        }
    }
}