package com.example.apbd

import androidx.room.Dao
import androidx.room.Insert
import com.example.apbd.data.Income

@Dao
interface IncomeDAO {
    @Insert
    fun insertData(vararg income :Income)

}