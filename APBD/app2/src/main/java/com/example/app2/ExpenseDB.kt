package com.example.app2

import android.net.Uri
import android.provider.BaseColumns

object ExpenseDB{
    class expenseTable : BaseColumns {
        companion object{
            val TABLE_EXPENSE = "tbl_expense"
            val COLUMN_ID = "expense_id"
            val COLUMN_DATE = "expense_date"
            val COLUMN_DESCRIPTION = "expense_desc"
            val COLUMN_AMOUNT = "expense_amount"
        }
    }
}

class myContentProviderURI{
    companion object{
        var AUTHORITY = "com.example.apbd.provider.provider.myContentProvider"
        var EXPENSE_TABLE = ExpenseDB.expenseTable.TABLE_EXPENSE
        val CONTENT_URI : Uri = Uri.parse("content://$AUTHORITY/$EXPENSE_TABLE")
    }
}