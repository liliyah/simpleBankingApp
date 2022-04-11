package banksystems.bank.bankapp.banking.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Transaction
import banksystems.bank.bankapp.banking.models.User

@Database(entities =  [User::class ,banksystems.bank.bankapp.banking.models.Transaction::class],version = 1, exportSchema = false)
abstract class UsersTransactionsDatabase :RoomDatabase() {
    abstract val usersDataDao: UsersDataDao
    abstract val transactionDataDao: TransactionDataDao

    companion object {

        @Volatile
        private var INSTANCE:UsersTransactionsDatabase? = null

        fun getInstance(context: Context): UsersTransactionsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,UsersTransactionsDatabase::class.java,
                        "database"
                    )
                        .fallbackToDestructiveMigration().createFromAsset("database/bank.db")
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}