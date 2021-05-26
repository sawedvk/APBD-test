package com.example.apbd.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Income(
        @PrimaryKey var _Id : Int,
        @ColumnInfo(name = "Column_Date") var Date : String = "",
        @ColumnInfo(name = "Column_Desc") var Desc : String = "",
        @ColumnInfo(name = "Column_Amount") var Amount : String = ""
)

