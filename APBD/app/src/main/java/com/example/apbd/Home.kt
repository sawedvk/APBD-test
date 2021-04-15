package com.example.apbd


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home.*
import java.util.*

class Home : AppCompatActivity() {

    var mAlarmManager: AlarmManager?=null
    var mPendingIntent: PendingIntent?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)


        var usr:User? = intent.getParcelableExtra<User>(EXTRA_USER)
        hai_name_.setText("Hello ${usr?.username} ")

        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
//            R.id.memes -> {
//                val intent = Intent(this, Memes::class.java)
//                startActivity(intent)
//            }
            R.id.Contact -> {
                val intent = Intent(this,ActivityDetail::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_overflow,menu)
        return true
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