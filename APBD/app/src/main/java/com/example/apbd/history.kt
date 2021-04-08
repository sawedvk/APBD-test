package com.example.apbd

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class history : AppCompatActivity() {

    var mAlarmManager: AlarmManager?=null
    var mPendingIntent: PendingIntent?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)
        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    fun goToIncome(view: View) {
        var intentIncome = Intent(this, income::class.java)
        startActivity(intentIncome)
    }
    fun goToExpense(view: View) {
        var intentexpense = Intent(this, expense::class.java)
        startActivity(intentexpense)
    }
    fun goToHome(view: View) {
        var intenthome = Intent(this,Home::class.java)
        startActivity(intenthome)
    }
    fun goToPlan(view: View) {
        var alarmTimer = Calendar.getInstance()
        alarmTimer.add(Calendar.SECOND, 5)
        var sendIntent = Intent(this, MyReceiverAlarm::class.java)
        mPendingIntent = PendingIntent.getBroadcast(this, 101, sendIntent, 0)
        mAlarmManager?.set(AlarmManager.RTC, alarmTimer.timeInMillis, mPendingIntent)
        var intenPlan = Intent(this,Planning::class.java)
        startActivity(intenPlan)
    }
    fun goToHistory(view: View) {
        var intenthistory = Intent(this,history::class.java)
        startActivity(intenthistory)
    }

    fun goToSettings(view: View) {
        var intentsettings = Intent(this,Settings::class.java)
        startActivity(intentsettings)
    }
    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
                .setMessage("New Transaction")
                .setPositiveButton("Income", DialogInterface.OnClickListener{ dialogInterface, i -> goToIncome(view)
                })
                .setNegativeButton("Expense", DialogInterface.OnClickListener{ dialogInterface, i -> goToExpense(view)
                })
        dialog.show()
    }
}