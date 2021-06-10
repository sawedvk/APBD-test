package com.example.apbd

import android.content.Context
import android.content.SharedPreferences

class SharedPrefWidget(context : Context) {

    private val Item_Name = "produk"
    private val Item_Date = "tanggal"
    private val Item_Harga ="harga"

    private val mySharedPref : SharedPreferences
    init {
        mySharedPref = context.getSharedPreferences("LatestTransaction",Context.MODE_PRIVATE)
    }

    var nama
        get() = mySharedPref.getString(Item_Name,null)
        set(value) {
        mySharedPref.edit().putString(Item_Name,value).commit()
        }

    var tanggal
        get() = mySharedPref.getString(Item_Date,null)
        set(value) {
            mySharedPref.edit().putString(Item_Date,value).commit()
        }

    var harga
        get() = mySharedPref.getString(Item_Harga,null)
        set(value) {
            mySharedPref.edit().putString(Item_Harga,value).commit()
        }
}