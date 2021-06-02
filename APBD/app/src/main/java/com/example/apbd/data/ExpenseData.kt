package com.example.apbd.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ExpenseData : Parcelable {
    var id :Int = 0
    var date :String = ""
    var desc :String = ""
    var amount :String = ""
}