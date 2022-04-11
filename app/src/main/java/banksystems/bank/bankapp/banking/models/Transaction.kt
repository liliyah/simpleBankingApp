package banksystems.bank.bankapp.banking.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(

    val from_customer: String,
    val to_customer: String,
    val transferrend_amount: String,
    val transaction_time: String,
) {
    @PrimaryKey(autoGenerate = true)
    var transaction_id:Int = 0
}