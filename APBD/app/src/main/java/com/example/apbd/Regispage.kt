package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Regispage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
    }

    fun goToConfirmRegis(view: View) {
        var intentConfirm = Intent(this, ConfirmRegis::class.java)
        startActivity(intentConfirm)
    }
}