package banksystems.bank.bankapp.banking.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import banksystems.bank.bankapp.banking.models.User
import banksystems.bank.bankapp.banking.room.UsersDataDao

class UsersViewModel(
    val database: UsersDataDao,
    application: Application,
) : AndroidViewModel(application) {
    private val _navigateTousersDetail = MutableLiveData<User?>()
    val navigateToUsersDetail:LiveData<User?>
        get() = _navigateTousersDetail


    fun onUserClicked(user: User) {
        _navigateTousersDetail.value = user
    }
    fun onUserDetailNavigated() {
        _navigateTousersDetail.value = null
    }
      suspend fun getAllUsersFromDataBase():List<User> {
        var users = database.GetAllUsersData()
          Log.d("usersdata", "getUsersData: ${users.size}")

          return users
    }


}