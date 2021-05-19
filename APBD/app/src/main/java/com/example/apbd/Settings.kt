package com.example.apbd

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.*
import android.text.format.Formatter
import android.view.View
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.settings_page.*
import java.io.File
import java.util.*

private var sp : SoundPool? = null
private var ButtonSaveSoundID = 0
private var ButtonCancelSoundID = 0

class Settings : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            createNewSoundPool()
        }
        else{
            createOldSoundPool()
        }
        sp?.setOnLoadCompleteListener { soundPool, id, status ->
            if(status != 0){
                Toast.makeText(this,"Gagal Load",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Load Berhasil",Toast.LENGTH_SHORT).show()
            }
        }
        ButtonSaveSoundID = sp?.load(this, R.raw.sound1,1) ?: 0
        ButtonCancelSoundID = sp?.load(this, R.raw.sound2,1) ?: 0
    }

    override fun onStop() {
        super.onStop()
        sp?.release()
        sp = null
    }


    private fun createOldSoundPool() {
        sp = SoundPool(15, AudioManager.STREAM_MUSIC, 0)
    }

    private fun createNewSoundPool() {
        sp = SoundPool.Builder().setMaxStreams(15).build()
    }

    private val Channel_ID = "trial_01"
    private val Notif_ID = 100

    var mAlarmManager: AlarmManager?=null
    var mPendingIntent: PendingIntent?=null
    private fun testSaveLocationExists(): Boolean {
        val sDCardStatus = Environment.getExternalStorageState()
        val status: Boolean

        // If SD card is mounted
        status = if (sDCardStatus == Environment.MEDIA_MOUNTED) {
            true
        } else {
            false
        }
        return status
    }
    private fun getAvailableExternalMemorySize(): String? {
        //panggil function yang mengecek apakah external memory terpasang
        return if (testSaveLocationExists()) {
            //tentukan path yaitu external storage
            val path: File = Environment.getExternalStorageDirectory()
            //fungsi StatFs akan membantu kita menjabarkan statistik atau keadaan saat ini dari path external storage yang kita berikan
            val stat = StatFs(path.getPath())

            //sebelum menentukan ukuran, kita harus telebih dahulu mencari tahu ukuran dari setiap block memory melalui stats yang telah didapat melalui fungsi StatFs
            val blockSize: Long = stat.getBlockSizeLong()
            //kita juga perlu tahu berapa block yang masih tersedia
            val availableBlocks: Long = stat.getAvailableBlocksLong()

            //untuk  mengetahui ukuran total , maka kita harus mengalikan jumlah block memory yang tersedia dengan ukuran setiap block
            //dengan begitu kita akan mendapatkan ukuran total memory dalam satuan byte

            formatSize(availableBlocks * blockSize)
        } else {
            ""
        }

    }


    private fun formatSize(size: Long): String? {

        var size = size
        var suffix: String? = null
        if (size >= 1024) {
            suffix = " KB"
            size /= 1024
            if (size >= 1024) {
                suffix = " MB"
                size /= 1024
            }
        }
        val resultBuffer =
            StringBuilder(java.lang.Long.toString(size))
        var commaOffset = resultBuffer.length - 3
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',')
            commaOffset -= 3
        }
        if (suffix != null) resultBuffer.append(suffix)
        return resultBuffer.toString()

        textView3.setText(resultBuffer)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_page)

        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        createNotificationChannel()

        exportexcel.setOnClickListener {
            sendNotification()
        }


        button2.setOnClickListener{
            if(ButtonSaveSoundID != 0){
                sp?.play(ButtonSaveSoundID,0.99f,0.99f,1,0,0.99f)
            }
        }

        var sendemailservice = Intent (this,SendDataMailService::class.java)
        sendemail.setOnClickListener {
            startService(sendemailservice)
        }

        button3.setOnClickListener {
            stopService(sendemailservice)
            if(ButtonCancelSoundID != 0){
                sp?.play(ButtonCancelSoundID,0.99f,0.99f,1,0,0.99f)
            }
        }

        checkStorage.setOnClickListener {
            if(testSaveLocationExists() == true)
            {
                val textView: TextView = findViewById(R.id.textView3)
                val stat = StatFs(Environment.getExternalStorageDirectory().path)
                val bytesAvailable = stat.blockSize.toLong() * stat.blockCount.toLong()
                val megAvailable = bytesAvailable / 1048576
                val path: File = Environment.getDataDirectory()
                val stat2 = StatFs(path.path)
                val blockSize = stat2.blockSize.toLong()
                val availableBlocks = stat2.availableBlocks.toLong()
                val format: String = Formatter.formatFileSize(this, availableBlocks * blockSize)

                Toast.makeText(this, format ,Toast.LENGTH_SHORT).show()
//                Toast.text = String.format("Available Memory: %s", format)
            }
        }
    }

    private  fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Test Channel"
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

        val intent = Intent(this, Settings::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        val Hideintent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notificationlayout = RemoteViews(packageName,R.layout.custom_notification)


//        val pendingIntent : PendingIntent = PendingIntent.getActivity(this, 0 ,intent,0 )
        val testIntent =Intent(this,Settings::class.java)

        val pendingIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(testIntent)
                .getPendingIntent(125,PendingIntent.FLAG_UPDATE_CURRENT)

        val progress_max = 100
        val progress_now = 0

        val builder = NotificationCompat.Builder(this, Channel_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Downloading Excel")
                .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationlayout)
                .setContentText("Download in progress")
                .setContentIntent(pendingIntent)
                .setNumber(1)
                .setOnlyAlertOnce(true)
                .addAction(R.drawable.ic_hide, "Open ", pendingIntent)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setPriority(NotificationCompat.PRIORITY_HIGH)


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