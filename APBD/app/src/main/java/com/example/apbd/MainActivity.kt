package com.example.apbd

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
        myToast(Pesan = "testrun").show()
    }

}