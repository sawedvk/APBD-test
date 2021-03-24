package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ConfirmRegis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_otpcode)
    }

    fun goToLogin(view: View) {
        var intentlogin = Intent(this, LoginPage::class.java)
        startActivity(intentlogin)
    }
}