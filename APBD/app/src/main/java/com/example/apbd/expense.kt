package com.example.apbd

import DatabaseStuffs.ExpDB_Helper
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.apbd.data.ExpenseData
import kotlinx.android.synthetic.main.expense.*
import kotlinx.android.synthetic.main.income.*
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

class expense : AppCompatActivity() {

    var mAlarmManager:AlarmManager?=null
    var mPendingIntent:PendingIntent?=null
    var mySQLitedb:ExpDB_Helper? = null

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
                    }
                }
            }
        } catch (e: FileNotFoundException) {
        } catch (e: IOException) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)

        mAlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mySQLitedb = ExpDB_Helper(this)

        var id = intent.getStringExtra("Id")
        var deskripsi =  intent.getStringExtra("Deskripsi")
        var tanggal = intent.getStringExtra("Tanggal")
        var nominal = intent.getStringExtra("Nominal")



        editTextDate.setText(tanggal)
        Description.setText(deskripsi)
        Amount.setText(nominal)

        recovery.setOnClickListener {
            readFileInternal()
        }

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        editTextDate.setOnClickListener {
            val picker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view: DatePicker?, mYear: Int, mMont: Int, Mday: Int ->
                editTextDate.setText(""+Mday+"/"+mMont+"/"+mYear)
            },year ,month, day)
            picker.show()
        }

        button.setOnClickListener {
            var ExpenseTmp = ExpenseData()
            ExpenseTmp.date=editTextDate.text.toString()
            ExpenseTmp.desc=Description.text.toString()
            ExpenseTmp.amount=Amount.text.toString()
            var result=mySQLitedb?.addExpense(ExpenseTmp)
            if (result!=-1L){
                Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
            val sharestuff = SharedPref(this, "itemData")

            sharestuff.setDate(editTextDate.text.toString())
            sharestuff.setProduct(Description.text.toString())
            sharestuff.setPrice(Amount.text.toString().toInt())

            editTextDate.text.clear()
            Description.text.clear()
            Amount.text.clear()
        }
    }

    fun updateAdapter(){

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