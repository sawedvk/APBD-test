package com.example.apbd

import android.content.DialogInterface
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

    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
            .setMessage("Save")
            .setPositiveButton("savePlan", DialogInterface.OnClickListener{dialogInterface, i -> Toast.makeText(this,"SAVE AND PLAN NOW!", Toast.LENGTH_LONG).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener{dialogInterface, i -> Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
            })
        dialog.show()
    }
}