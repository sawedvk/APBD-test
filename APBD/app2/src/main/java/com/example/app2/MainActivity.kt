package com.example.app2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tmp = expenseTransaction(this)
        var result = ""
        for (str in tmp.viewAllData()){
            result += str + System.getProperty("line.seperator")
        }
        textDesc.text = result
    }
}