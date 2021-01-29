package com.example.apbd

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class expense : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)
    }

    fun goToHome(view: View) {
        var intenthome = Intent(this,Home::class.java)
        startActivity(intenthome)
    }


    fun alertKonfirmasi(view: View) {
        var dialog = AlertDialog.Builder(this)
            .setMessage("You Almost Exceeded Your Plan!")
                .setPositiveButton("Save", DialogInterface.OnClickListener{ dialogInterface, i -> goToHome(view)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener{ dialogInterface, i -> Toast.makeText(this,"CANCEL", Toast.LENGTH_LONG).show()
                })
        dialog.show()
    }
}