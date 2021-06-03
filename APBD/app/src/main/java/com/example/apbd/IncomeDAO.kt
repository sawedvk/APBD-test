package com.example.apbd

import androidx.room.*
import com.example.apbd.data.Income

@Dao
interface IncomeDAO {


    @Query("Select * from Income")
    fun getAll() : List<Income>

    @Insert
    fun insertData(vararg income :Income)

    @Update
    fun update(income : Income)
    
    @Transaction
    fun TransactionData(): List<Income> {
        var transactionData = getAll()
        return transactionData
    }
    @Transaction
    fun insertTransactionData(income: Income){
        insertData();
    }
    @Transaction
    fun UpdateTransactionData(income: Income){
        update(income);
    }
}