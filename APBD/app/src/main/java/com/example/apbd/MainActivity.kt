package com.example.apbd

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.apbd.Fragment.FragmentLogin

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.boot_up)

        val fragmentlogin = FragmentLogin()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragmentlogin ).commit()
    }

    override fun  kirimData(editEdit:String){

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