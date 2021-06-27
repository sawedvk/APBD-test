package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.register_page.*
import kotlinx.android.synthetic.main.register_page.buttonRegister2
import kotlinx.android.synthetic.main.register_page.editTextTextEmailAddress
import kotlinx.android.synthetic.main.register_page.editTextTextPassword

class Regispage : AppCompatActivity() {
    lateinit var controller: FirebaseController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_page)
        controller = FirebaseController(this)

        buttonRegister2.setOnClickListener {
            if (TextUtils.isEmpty(editTextTextEmailAddress.text.toString())) {
                Toast.makeText(this, "Please Enter your Email!", Toast.LENGTH_SHORT).show()
            } else {
                if (!Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.text).matches()) {
                    Toast.makeText(this, "Please Enter a valid Email!", Toast.LENGTH_SHORT).show()
                } else {
                    if (TextUtils.isEmpty(editTextTextPassword.text.toString())) {
                        Toast.makeText(this, "Please Enter your Password!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        var intentConfirm = Intent(this, LoginPage::class.java)
                        startActivity(intentConfirm)
                        addnewUser()
                    }
                }
            }
        }
    }


    private fun addnewUser() {

        var emailAdress = findViewById<EditText>(R.id.editTextTextEmailAddress)
        var userPass = findViewById<EditText>(R.id.editTextTextPassword)
        controller.newUser(userData(emailAdress.text.toString(),userPass.text.toString()))
    }
}