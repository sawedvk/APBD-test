package com.example.apbd

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Planning : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.planning)
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
    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
            .setMessage("New Transaction")
            .setPositiveButton("Income", DialogInterface.OnClickListener{ dialogInterface, i -> Toast.makeText(this,"INCOME", Toast.LENGTH_LONG).show()
            })
            .setNegativeButton("Expense", DialogInterface.OnClickListener{ dialogInterface, i -> Toast.makeText(this,"EXPENSE", Toast.LENGTH_LONG).show()
            })
        dialog.show()
    }
}