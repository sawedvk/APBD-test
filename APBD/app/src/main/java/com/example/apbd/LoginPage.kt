    package com.example.apbd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apbd.Fragment.FragmentHome
import com.example.apbd.Fragment.FragmentLogin
import com.example.apbd.Fragment.InterfaceData
private const val The_Key ="Key"

    class LoginPage : AppCompatActivity(), InterfaceData {
//    private fun myToast(Pesan: String,Waktu: Int= Toast.LENGTH_SHORT)
//            = Toast.makeText(this,Pesan,Waktu)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val fragmentlogin = FragmentLogin()
//
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragmentlogin ).commit()

//        editTextTextEmailAddress.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (count == 0)
//                {
//                    EmailValidation.setText("Please input the email")
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//            }
//        })
//
//        buttonRegisterGoogle2.setOnClickListener {
//            val fragmenthome = FragmentHome()
//            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragmenthome).commit()
//        }

//        buttonRegisterGoogle2.setOnClickListener {
//            var Intent2Home = Intent(this, Home::class.java)
//            var usr=User(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
//            Intent2Home.putExtra(EXTRA_USER,usr)
//            startActivity(Intent2Home)
//        }

    }
//
//        override fun onSaveInstanceState(outState: Bundle) {
//            super.onSaveInstanceState(outState)
//            outState.putString(The_Key, EmailValidation.text.toString())
//        }
//
//        override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//            super.onRestoreInstanceState(savedInstanceState)
//            EmailValidation.text = savedInstanceState.getString(The_Key) ?: "Empty"
//        }

//    fun goToHome(view: View) {
//        myToast(Pesan = "Login Succesful").show()
//        var intenthome = Intent(this,Home::class.java)
//        startActivity(intenthome)
//    }
//
//    fun goToRegis(view: View){
//        var intentRegis = Intent(this,Regispage::class.java)
//        startActivity(intentRegis)
//    }

        override fun kirimData(editEdit: String) {

            val bundle = Bundle()
            bundle.putString("email", editEdit)

            val transaksi = this.supportFragmentManager.beginTransaction()

            val fragmentHome = FragmentHome()
            fragmentHome.arguments = bundle

            transaksi.replace(R.id.fragmentContainer, fragmentHome)
            transaksi.commit()
        }
    }