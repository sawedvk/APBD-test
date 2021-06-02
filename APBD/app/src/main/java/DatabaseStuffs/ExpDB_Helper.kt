package DatabaseStuffs

import DBsource.ExpenseDB
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.apbd.data.ExpenseData

class ExpDB_Helper(context: Context) : SQLiteOpenHelper(
        context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private val DATABASE_NAME = "mysqllite.db"
        private val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_EXPENSE_TABLE = "CREATE TABLE ${ExpenseDB.expenseTable.TABLE_EXPENSE}" +
                "(${ExpenseDB.expenseTable.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${ExpenseDB.expenseTable.COLUMN_DATE} TEXT," +
                "${ExpenseDB.expenseTable.COLUMN_DESCRIPTION} TEXT," +
                "${ExpenseDB.expenseTable.COLUMN_AMOUNT} TEXT)"
        db?.execSQL(CREATE_EXPENSE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${ExpenseDB.expenseTable.TABLE_EXPENSE}")
        onCreate(db)
    }

    fun addExpense(expenseData: ExpenseData) : Long {
        var db = this.writableDatabase
        var contentValues = ContentValues().apply {
            put(ExpenseDB.expenseTable.COLUMN_DATE, expenseData.date)
            put(ExpenseDB.expenseTable.COLUMN_DESCRIPTION, expenseData.desc)
            put(ExpenseDB.expenseTable.COLUMN_AMOUNT, expenseData.amount)
        }
        var success = db.insert(ExpenseDB.expenseTable.TABLE_EXPENSE,null, contentValues)
        db.close()
        return success
    }

    fun viewAllExpense():List<ExpenseData>{
        val expenseList = ArrayList<ExpenseData>()
        val SELECT_EXPENSE = "SELECT * FROM " +
                "${ExpenseDB.expenseTable.TABLE_EXPENSE}"
        var db = this.readableDatabase
        var cursor : Cursor? =null
        try {
            cursor=db.rawQuery(SELECT_EXPENSE, null)
        }
        catch(e:SQLException){
            return ArrayList()
        }
        var expenseDesc = ExpenseData()
        if(cursor.moveToFirst()){
            do{
                expenseDesc.desc = cursor.getString(cursor.getColumnIndex(ExpenseDB.expenseTable.COLUMN_DESCRIPTION))
                expenseDesc.date = cursor.getString(cursor.getColumnIndex(ExpenseDB.expenseTable.COLUMN_DATE))
                expenseDesc.amount = cursor.getString(cursor.getColumnIndex(ExpenseDB.expenseTable.COLUMN_AMOUNT))
                expenseList.add(expenseDesc)
            }while (cursor.moveToNext())
        }
        return expenseList
    }

    fun deleteExpense(desc:String){
        var db = this.writableDatabase
        var selection = "${ExpenseDB.expenseTable.COLUMN_DESCRIPTION} = ?"
        var selectionArgs = arrayOf(desc)
        db.delete(ExpenseDB.expenseTable.TABLE_EXPENSE,selection,selectionArgs)
    }
}