package DBsource

import android.provider.BaseColumns

object Income_data{
    class IncomeTable : BaseColumns{
        companion object{
            val Table_Income = "tbl_Income"
            val Column_Date ="Income_Date"
            val Column_Description ="Income_Desc"
            val Column_Amount ="Income_Amount"
        }
    }
}