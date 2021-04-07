package com.example.apbd

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class expense : AppCompatActivity() {

    var mAlarmManager:AlarmManager?=null
    var mPendingIntent:PendingIntent?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)

        mAlarmManager=getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var alarmTimer = Calendar.getInstance()
        alarmTimer.add(Calendar.SECOND, 20)
        var sendIntent = Intent(this,MyReceiverAlarm::class.java)
        mPendingIntent = PendingIntent.getBroadcast(this,101,sendIntent,0)
        mAlarmManager?.set(AlarmManager.RTC,alarmTimer.timeInMillis,mPendingIntent)

        fun goToHome(view: View) {
            var intenthome = Intent(this,Home::class.java)
            startActivity(intenthome)
        }

        fun alertKonfirmasi(view: View) {
            var dialog = AlertDialog.Builder(this)
                .setMessage("You Almost Exceeded Your Plan!")
                .setPositiveButton("Save", DialogInterface.OnClickListener{ dialogInterface, i -> goToHome(view)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialogInterface, i ->
                    if (mPendingIntent!=null){
                        mAlarmManager?.cancel(mPendingIntent)
                        mPendingIntent?.cancel()
                    }
                    Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
                })
            dialog.show()
        }
    }
}