package banksystems.bank.bankapp.banking.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import banksystems.bank.bankapp.banking.room.UsersDataDao
import banksystems.bank.bankapp.banking.room.UsersTransactionsDatabase

class UsersViewModelFactory(
                            private val dataSource: UsersDataDao,
                            private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}