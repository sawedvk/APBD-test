package com.example.apbd

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.BroadcastReceiver
import android.provider.Telephony


class ConfirmRegis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_otpcode)

        var SMSReceive = SMSReceiver()
        var filter = IntentFilter()
        filter.addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        registerReceiver(SMSReceive,filter)
    }

    fun goToLogin(view: View) {
        var intentlogin = Intent(this, LoginPage::class.java)
        startActivity(intentlogin)
    }
}