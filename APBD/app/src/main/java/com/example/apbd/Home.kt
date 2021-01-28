package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
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
}