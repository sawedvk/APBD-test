package com.example.apbd.provider

import DBsource.ExpenseDB
import DatabaseStuffs.ExpDB_Helper
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.util.Log

class myContentProvider : ContentProvider(){
    private  var dbHelper:ExpDB_Helper?=null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(uri: Uri,
                       projection: Array<out String>?,
                       selection: String?,
                       selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor? {
        var queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables=ExpenseDB.expenseTable.TABLE_EXPENSE
        var cursor : Cursor = queryBuilder.query(dbHelper?.readableDatabase,
        projection,selection,selectionArgs,null,null,sortOrder)
        cursor.setNotificationUri(context?.contentResolver,uri)
        Log.i("cursortest",cursor.toString())
        return  cursor
    }

    override fun onCreate(): Boolean {
        dbHelper= ExpDB_Helper(context!!)
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    companion object{
        var AUTHORITY = "com.example.apbd.provider.provider.myContentProvider"
        var EXPENSE_TABLE = ExpenseDB.expenseTable.TABLE_EXPENSE
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$EXPENSE_TABLE")
        var haiya = "pusing"

    }
}