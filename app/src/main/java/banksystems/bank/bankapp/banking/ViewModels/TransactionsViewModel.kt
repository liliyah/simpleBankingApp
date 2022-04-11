package banksystems.bank.bankapp.banking.ViewModels

import android.app.Application
import android.util.Log
import android.view.SurfaceControl
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import banksystems.bank.bankapp.banking.models.Transaction
import banksystems.bank.bankapp.banking.models.User
import banksystems.bank.bankapp.banking.room.TransactionDataDao
import banksystems.bank.bankapp.banking.room.UsersDataDao
import kotlinx.coroutines.launch

class TransactionsViewModel(
    val database: TransactionDataDao,
    application: Application,
) : AndroidViewModel(application) {
    var TransactionLists: LiveData<List<Transaction>> = database.GetAllTransctionsData()
    private suspend fun insertTransaction(transaction: Transaction) {
        database.InsertTransction(transaction)
    }

    fun insertTransactionIndatabase(insertTransaction: Transaction) {
        viewModelScope.launch {
            Log.d("insert", "insertTransactionIndatabase: ${insertTransaction.toString()}")
            insertTransaction(insertTransaction)
        }

    }





}