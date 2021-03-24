package com.example.apbd

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.settings_page.*

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_page)

        var exportservice = Intent (this,ExportDataService::class.java)
        exportexcel.setOnClickListener {
            startService(exportservice)
        }

        var sendemailservice = Intent (this,SendDataMailService::class.java)
        sendemail.setOnClickListener {
            startService(sendemailservice)
        }

        button3.setOnClickListener {
            stopService(exportservice)
            stopService(sendemailservice)
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