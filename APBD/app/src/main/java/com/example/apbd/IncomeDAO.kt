package com.example.apbd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.apbd.data.Income

@Dao
interface IncomeDAO {


    @Query("Select * from Income")
    fun getAll() : List<Income>

    @Insert
    fun insertData(vararg income :Income)

}