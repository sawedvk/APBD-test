package com.example.apbd

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class income : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.income)
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
                Thread(Runnable {
                    for (i in 0..10)
                        try {
                            Thread.sleep(50L)
                        } catch (e: Exception) {

                        }
                }).start()
                goToPlan(view)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{dialogInterface, i -> Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
            })
        dialog.show()
    }
}