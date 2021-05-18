package com.example.apbd

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.system.Os.write
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.expense.*
import kotlinx.android.synthetic.main.income.*
import java.io.File
import java.util.jar.Manifest

class income : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()

        writeFileExternal()
    }

    private fun writeFileExternal() {
//        var total = "${::IncomeDescription.name}:${IncomeDescription.text}\n${::IncomeAmount.name}:${IncomeAmount.text}"
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        if (!myDir.exists()) {
            myDir.mkdir()
        }
        File(myDir, "Income.txt").apply {
            writeText(IncomeDescription.text.toString())
            writeText(IncomeAmount.text.toString())
        }
        IncomeDescription.text.clear()
    }

    private fun readFileExternal() {
        var myDir = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.toURI())
        IncomeDescription.text.clear()
        var readFile = ""
        File(myDir,"Income.txt").bufferedReader().useLines {
            readFile += "$it\n"
        }
        IncomeDescription.setText(readFile)

    }

    private fun isExternalStorageReadable():Boolean{
        if(ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED
            ){
            requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),1234)
        }
        var state = Environment.getExternalStorageState()
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            1234 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.income)

        buttonSave.setOnClickListener {
            if(isExternalStorageReadable()){
                writeFileExternal()
            }
        }
    }

    fun goToHome(view: View) {
        var intenthome = Intent(this,Home::class.java)
        startActivity(intenthome)
    }
    fun goToPlan(view: View) {
        var intenPlan = Intent(this,Planning::class.java)
        startActivity(intenPlan)
    }

    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
            .setMessage("Save")
            .setPositiveButton("Save and Plan", DialogInterface.OnClickListener{dialogInterface, i ->
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
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{dialogInterface, i -> Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
            })
        dialog.show()
    }
}