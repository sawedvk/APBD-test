package com.example.apbd

import android.app.Application
import com.google.android.gms.ads.interstitial.InterstitialAd

class PercobaanAds : Application() {
    companion object{
        var mInterAds : InterstitialAd? = null
    }
}