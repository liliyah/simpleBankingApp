package banksystems.bank.bankapp.banking.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import banksystems.bank.bankapp.banking.models.User
@Dao
interface UsersDataDao {


    @Query("SELECT * from user_table")
    suspend fun GetAllUsersData():List<User>
}