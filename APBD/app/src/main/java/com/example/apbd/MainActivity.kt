package com.example.apbd

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.boot_up)
        var AirRecieve = AirplaneReciever()
        var filter = IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(AirRecieve,filter)


    }


    fun goToLogin(view: View) {
        var intentlogin = Intent(this,LoginPage::class.java)
        startActivity(intentlogin)
    }
    fun goToRegis(view: View) {
        var intentRegis = Intent(this, Regispage::class.java)
        startActivity(intentRegis)
    }


}