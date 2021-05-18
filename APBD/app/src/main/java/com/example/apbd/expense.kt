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
import kotlinx.android.synthetic.main.expense.*
import java.io.FileNotFoundException
import java.io.IOException

class expense : AppCompatActivity() {

    var mAlarmManager:AlarmManager?=null
    var mPendingIntent:PendingIntent?=null

    override fun onDestroy() {
        super.onDestroy()

        writeFileInternal()
    }

    private fun writeFileInternal() {
        var total = "${::Description.name}:${Description.text}\n${::Amount.name}:${Amount.text}"
        var output = openFileOutput("expense.txt", Context.MODE_PRIVATE).apply {
            write(total.toByteArray())
            close()
        }
    }

    private fun readFileInternal(){
        try {
            openFileInput("expense.txt").apply {
                bufferedReader().useLines {
                    for (text in it.toList()) {
                        var keyValArray = text.split(":").toTypedArray()
                        if (keyValArray[0]=="${::Description.name}"){
                            Description.setText(keyValArray[1])
                        }
                        else if (keyValArray[0]=="${::Amount.name}"){
                            Amount.setText(keyValArray[1])
                        }
//                        edit_text1.setText("${edit_text1.text}\n$text")
                    }
                }
            }
        } catch (e: FileNotFoundException) {
//            edit_text1.setText("File Not Found")
        } catch (e: IOException) {
//            edit_text1.setText("File Can't be Read")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)

        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        recovery.setOnClickListener {
            readFileInternal()
        }
    }
        fun goToHome(view: View) {
            var intenthome = Intent(this,Home::class.java)
            startActivity(intenthome)
        }

        fun alertKonfirmasi(view: View) {
            var dialog = AlertDialog.Builder(this)
                .setMessage("You Almost Exceeded Your Plan!")
                .setPositiveButton("Save", DialogInterface.OnClickListener{ dialogInterface, i ->
//                    var alarmTimer = Calendar.getInstance()
//                    alarmTimer.add(Calendar.SECOND, 20)
//                    var sendIntent = Intent(this, MyReceiverAlarm::class.java)
//                    mPendingIntent = PendingIntent.getBroadcast(this, 101, sendIntent, 0)
//                    mAlarmManager?.set(AlarmManager.RTC, alarmTimer.timeInMillis, mPendingIntent)
                    goToHome(view)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialogInterface, i ->
//                    if (mPendingIntent!=null){
//                        mAlarmManager?.cancel(mPendingIntent)
//                        mPendingIntent?.cancel()
//                    }
                    Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
                })
            dialog.show()
            val sharestuff = SharedPref(this, "itemData")

            sharestuff.setDate(editTextDate.text.toString())
            sharestuff.setProduct(Description.text.toString())
            sharestuff.setPrice(Amount.text.toString().toInt())
        }


}