package banksystems.bank.bankapp.banking.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import banksystems.bank.bankapp.banking.models.Transaction
import banksystems.bank.bankapp.banking.models.User
@Dao
interface TransactionDataDao {
    @Insert
    suspend  fun InsertTransction(transaction: Transaction )
    @Query("SELECT * from transaction_table")
      fun GetAllTransctionsData():LiveData<List<Transaction>>
}