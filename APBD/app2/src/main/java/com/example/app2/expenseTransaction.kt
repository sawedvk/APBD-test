package com.example.app2

import android.content.Context
import com.example.app2.ExpenseDB.expenseTable.Companion.COLUMN_AMOUNT
import com.example.app2.ExpenseDB.expenseTable.Companion.COLUMN_DATE
import com.example.app2.ExpenseDB.expenseTable.Companion.COLUMN_DESCRIPTION
import com.example.app2.ExpenseDB.expenseTable.Companion.COLUMN_ID

class expenseTransaction(context: Context){
    private val myContentResolver = context.contentResolver

    fun viewAllData() : List<String>{
        var myDataList = ArrayList<String>()
        var mProjection = arrayOf(COLUMN_ID, COLUMN_DESCRIPTION, COLUMN_DATE, COLUMN_AMOUNT)
        var cursor = myContentResolver.query(
            myContentProviderURI.CONTENT_URI, mProjection, null, null, null
        )
        if(cursor!=null){
            var expenseDesc = ""
            if(cursor.moveToFirst()){
                do{
                    expenseDesc = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                    myDataList.add(expenseDesc)
                }while (cursor.moveToNext())
            }
        }
        return myDataList
    }
}