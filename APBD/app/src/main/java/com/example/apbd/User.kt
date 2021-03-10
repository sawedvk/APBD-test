package com.example.apbd

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (var username : String = "No-Name",
                 var password : String = "password"):Parcelable {
}