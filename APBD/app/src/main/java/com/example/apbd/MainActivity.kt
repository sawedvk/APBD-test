package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

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
}