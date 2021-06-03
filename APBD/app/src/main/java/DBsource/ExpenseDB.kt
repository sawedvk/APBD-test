package DBsource

import android.provider.BaseColumns

object ExpenseDB{
    class expenseTable : BaseColumns{
        companion object{
            val TABLE_EXPENSE = "tbl_expense"
            val COLUMN_ID = "expense_id"
            val COLUMN_DATE = "expense_date"
            val COLUMN_DESCRIPTION = "expense_desc"
            val COLUMN_AMOUNT = "expense_amount"
        }
    }
}