    package com.example.apbd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.login_page.*
private const val The_Key ="Key"
    class LoginPage : AppCompatActivity() {
    private fun myToast(Pesan: String,Waktu: Int= Toast.LENGTH_SHORT)
            = Toast.makeText(this,Pesan,Waktu)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        editTextTextEmailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count == 0)
                {
                    EmailValidation.setText("Please input the email")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

    }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putString(The_Key, EmailValidation.text.toString())
        }

        override fun onRestoreInstanceState(savedInstanceState: Bundle) {
            super.onRestoreInstanceState(savedInstanceState)
            EmailValidation.text = savedInstanceState.getString(The_Key) ?: "Empty"
        }

    fun goToHome(view: View) {
        myToast(Pesan = "Login Succesful").show()
        var intenthome = Intent(this,Home::class.java)
        startActivity(intenthome)
    }

    fun goToRegis(view: View){
        var intentRegis = Intent(this,Regispage::class.java)
        startActivity(intentRegis)
    }
        }