package com.example.apbd

import android.content.Context
import android.content.SharedPreferences

class SharedPref(val context: Context, val itemDetails: String) {
    val Item_Name = "Product"
    val Item_Date = "Date"
    val Item_Harga ="Price"

    private fun myprefs(): SharedPreferences {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(itemDetails,Context.MODE_PRIVATE)
        return sharedPreferences
    }

    fun getProduct(): String? {
        return myprefs().getString(Item_Name, null)
    }


    fun setProduct(productName : String) {

        myprefs().edit().apply {

            putString(Item_Name, productName )


            apply()
        }

    }
    fun getDate(): String? {
        return myprefs().getString(Item_Date, null)
    }


    fun setDate(productDate : String) {

        myprefs().edit().apply {
            putString(Item_Date, productDate)
            apply()
        }
    }

    fun getPrice(): Int? {
        return myprefs().getInt(Item_Harga, 0)
    }


    fun setPrice(productPrice : Int) {

        myprefs().edit().apply {

            putInt(Item_Harga, productPrice)

            apply()
        }
    }
}