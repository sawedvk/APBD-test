package com.example.apbd.provider

import DBsource.Income_data
import DatabaseStuffs.DB_Helper
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class myContentProvider : ContentProvider(){
    private  var dbHelper:DB_Helper?=null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        TODO("Not yet implemented")
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {

    }

    companion object{
        var AUTHORITY = "com.example.apbd.provider.provider.myContentProvider"
        var INCOME_TABLE = Income_data.IncomeTable.Table_Income
        val CONTENT_URI = Uri.parse("content://$AUTHORITY/$INCOME_TABLE")


    }
}