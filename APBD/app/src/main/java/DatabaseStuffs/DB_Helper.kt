package DatabaseStuffs

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apbd.IncomeDAO
import com.example.apbd.data.Income


@Database(entities = arrayOf(Income::class), version =  1)

abstract class DB_Helper : RoomDatabase() {
    abstract  fun incomeDao() : IncomeDAO
}