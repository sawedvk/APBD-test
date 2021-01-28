package com.example.apbd

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_page)
    }

    fun alertKonfirmasi(view: View){
        var dialog = AlertDialog.Builder( this)
            .setMessage("Logout ?")
            .setPositiveButton("Logout", DialogInterface.OnClickListener{ dialogInterface, i -> Toast.makeText(this,"LOGOUT", Toast.LENGTH_LONG).show()
            })
            .setNegativeButton("Logout", DialogInterface.OnClickListener{ dialogInterface, i -> Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
            })
        dialog.show()
    }
}