package banksystems.bank.bankapp.banking.utils

import banksystems.bank.bankapp.banking.models.User

class UsersListListner(val clickListener: (user: User) -> Unit) {
    fun onClick(user:User)=clickListener(user)
}