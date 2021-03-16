package com.example.apbd

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.apbd.Fragment.FragmentLogin
import com.example.apbd.Fragment.InterfaceData

class MainActivity : AppCompatActivity(), InterfaceData{

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.boot_up)

        val fragmentlogin = FragmentLogin()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragmentlogin ).commit()
    }


    fun goToLogin(view: View) {
        var intentlogin = Intent(this,LoginPage::class.java)
        startActivity(intentlogin)
    }
    fun goToRegis(view: View) {
        var intentRegis = Intent(this, Regispage::class.java)
        startActivity(intentRegis)
    }

    override fun kirimData(editEditText: String) {
        TODO("Not yet implemented")
        val bundle = Bundle()
        bundle.putString("Email", editEditText)

        val transaksi = this.supportFragmentManager.beginTransaction()

        val FragmentHome = FragmentLogin()
        FragmentHome.arguments = bundle

        transaksi.replace(R.id.fragmentContainer, FragmentHome)
        transaksi.commit()
    }


}