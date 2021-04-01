package com.example.apbd

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.settings_page.*

class Settings : AppCompatActivity() {

    private val Channel_ID = "trial_01"
    private val Notif_ID = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_page)

        createNotificationChannel()

        exportexcel.setOnClickListener {
            sendNotification()
        }




        var sendemailservice = Intent (this,SendDataMailService::class.java)
        sendemail.setOnClickListener {
            startService(sendemailservice)
        }

        button3.setOnClickListener {
            stopService(sendemailservice)
        }
    }
    private  fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Notification Title"
            val desc =" Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(Channel_ID,name,importance).apply {
                description = desc
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification() {

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notificationlayout = RemoteViews(packageName,R.layout.custom_notification)

        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0 ,intent,0 )

        val progress_max = 100
        val progress_now = 0

        val builder = NotificationCompat.Builder(this, Channel_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Downloading Excel")
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationlayout)
                .setContentText("Download in progress")
//                .setContentIntent(pendingIntent)
//                .setNumber(1)
                .setOnlyAlertOnce(true)
//                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(this).apply {
            builder.setProgress(progress_max, progress_now,false)
        }) {
            notify(Notif_ID, builder.build())

            Thread(Runnable {
                SystemClock.sleep(2000)
                var progress = 0
                while (progress <= progress_max) {
                    SystemClock.sleep(1000)
                    progress += 10

                    builder.setContentText(progress.toString() + "%")
                            .setProgress(progress_max, progress, false)

                    notify(Notif_ID, builder.build())
                }
                builder.setContentText("Download Complete")
                        .setProgress(0, 0, false)
                notify(Notif_ID, builder.build())
            }).start()
        }
    }

    fun goToBootUp(view: View) {
        var intentbootup = Intent(this,MainActivity::class.java)
        startActivity(intentbootup)
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
    fun alertIncomeExpense(view: View){
        var dialog = AlertDialog.Builder( this)
                .setMessage("New Transaction")
                .setPositiveButton("Income", DialogInterface.OnClickListener{ dialogInterface, i -> goToIncome(view)
                })
                .setNegativeButton("Expense", DialogInterface.OnClickListener{ dialogInterface, i -> goToExpense(view)
                })
        dialog.show()
    }

    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
            .setMessage("Logout ?")
            .setPositiveButton("Logout", DialogInterface.OnClickListener{ dialogInterface, i -> goToBootUp(view)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialogInterface, i -> Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
            })
        dialog.show()
    }
}