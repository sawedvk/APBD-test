package com.example.apbd.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Memes(
    val name : String,
    val url : String
) : Parcelable