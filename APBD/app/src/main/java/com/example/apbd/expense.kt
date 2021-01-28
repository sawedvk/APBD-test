package com.example.apbd

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class expense : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.expense)
    }

    fun alertKonfirmasi(view: View) {
        var dialog = AlertDialog.Builder(this)
            .setMessage("You Almost Exceeded Your Plan!")
        dialog.show()
    }
}