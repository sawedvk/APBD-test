package com.example.apbd

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private fun myToast(Pesan: String,Waktu: Int= Toast.LENGTH_SHORT)
            = Toast.makeText(this,Pesan,Waktu)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.boot_up)
    }
    fun showToast(view: View) {
        myToast(Pesan = "Login Succesful").show()
        var intentlogin = Intent(this,LoginPage::class.java)
        startActivity(intentlogin)
    }
    fun goToRegis(view: View) {
        var intentRegis = Intent(this, Regispage::class.java)
        startActivity(intentRegis)
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