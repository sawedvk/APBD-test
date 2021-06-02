package com.example.app2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Expense(var id :Int = 0,
    var date :String = "",
    var desc :String = "",
    var amount :String = "") : Parcelable{

}