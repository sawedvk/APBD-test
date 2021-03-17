package com.example.apbd


import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)


//        var usr:User? = intent.getParcelableExtra<User>(EXTRA_USER)
//        hai_name_.setText("Hello ${usr?.username} ")

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