package com.example.apbd

import DatabaseStuffs.DB_Helper
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.example.apbd.data.Income
import kotlinx.android.synthetic.main.income.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.io.File
import kotlin.random.Random


class income : AppCompatActivity() {

    private fun writeFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        if (!myDir.exists()) {
            myDir.mkdir()
        }
        File(myDir, "${IncomeDescription.text}.txt").apply {
            writeText(IncomeAmount.text.toString())
        }


        IncomeAmount.text.clear()
    }


    private fun readFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        IncomeAmount.text.clear()
        var readFile = ""
        File(myDir, "${IncomeDescription.text}.txt").forEachLine {
            readFile += "$it\n"
        }
        IncomeAmount.setText(readFile)

    }

    private fun isExternalStorageReadable(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1234)
        }
        var state = Environment.getExternalStorageState()
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(
                state
            )
        ) {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            1234 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.income)

        var db = Room.databaseBuilder(
            this,
            DB_Helper::class.java,
            "Income.db"
        ).build()

        var id = intent.getStringExtra("Id")
        var deskripsi =  intent.getStringExtra("Deskripsi")
        var tanggal = intent.getStringExtra("Tanggal")
        var nominal = intent.getStringExtra("Nominal")

        editTextDate1.setText(tanggal)
        IncomeDescription.setText(deskripsi)
        IncomeAmount.setText(nominal)


        buttonSave.setOnClickListener {
            if (isExternalStorageReadable()) {
                writeFileExternal()
            }
        }

        buttonRecovery.setOnClickListener {
            if (isExternalStorageReadable()) {
                readFileExternal()
            }
        }

        buttonSave3.setOnClickListener {
            Log.w("Hasil Testing", id!!.toString())
            var result = ""
            doAsync {
                var IncomeTMP = Income(id!!.toInt())
                IncomeTMP.Date = editTextDate1.text.toString()
                IncomeTMP.Desc = IncomeDescription.text.toString()
                IncomeTMP.Amount = IncomeAmount.text.toString()
                db.incomeDao().update(IncomeTMP)

                uiThread {
                    Toast.makeText(this@income, "Data Added", Toast.LENGTH_SHORT).show()
                    Log.w("Hasil Testing", result)
                }

            }


        }

        buttonSave.setOnClickListener {
            var result = ""
            var list = arrayListOf<Income>()
            doAsync {
                var IncomeTMP = Income(Random.nextInt())
                IncomeTMP.Date = editTextDate1.text.toString()
                IncomeTMP.Desc = IncomeDescription.text.toString()
                IncomeTMP.Amount = IncomeAmount.text.toString()
                db!!.incomeDao().insertData(IncomeTMP)
                uiThread {
                    Toast.makeText(this@income, "Data Added", Toast.LENGTH_SHORT).show()
                    Log.w("Hasil Testing", result)
                }

            }
        }


        fun goToHome(view: View) {
            var intenthome = Intent(this, Home::class.java)
            startActivity(intenthome)
        }

        fun goToPlan(view: View) {
            var intenPlan = Intent(this, Planning::class.java)
            startActivity(intenPlan)
        }

        fun alertKonfirmasi(view: View) {
            var dialog = AlertDialog.Builder(this)
                .setMessage("Save")
                .setPositiveButton(
                    "Save and Plan",
                    DialogInterface.OnClickListener { dialogInterface, i ->
//                Thread(Runnable {
//                    for (i in 0..10)
//                        try {
//                            Thread.sleep(50L)
//                        } catch (e: Exception) {
//
//                        }
//                }).start()
                        goToPlan(view)
                    })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                    Toast.makeText(this, "CANCEL", Toast.LENGTH_LONG).show()
                })
            dialog.show()
        }
    }
}