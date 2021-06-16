package com.example.apbd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_banner_ads.*

class BannerAds : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ads)

        MobileAds.initialize(this) {
            adView.loadAd(AdRequest.Builder().build())

            adView.adListener = object :AdListener()
            {

            }
        }
    }
}