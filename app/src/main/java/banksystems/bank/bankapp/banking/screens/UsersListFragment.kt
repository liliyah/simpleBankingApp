package banksystems.bank.bankapp.banking.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import banksystems.bank.bankapp.banking.Adapters.UserListAdapter
import banksystems.bank.bankapp.banking.R
import banksystems.bank.bankapp.banking.ViewModels.UsersViewModel
import banksystems.bank.bankapp.banking.ViewModels.UsersViewModelFactory
import banksystems.bank.bankapp.banking.databinding.FragmentUsersListBinding
import banksystems.bank.bankapp.banking.models.User
import banksystems.bank.bankapp.banking.room.UsersTransactionsDatabase
import banksystems.bank.bankapp.banking.utils.UsersListListner
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class UsersListFragment : Fragment() {
    private lateinit var usersList: List<User>
    private lateinit var user: User
    private lateinit var users_listViewModel: UsersViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding: FragmentUsersListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_users_list, container, false)

        usersList = ArrayList<User>()
        val application = requireNotNull(this.activity).application
        val dataSource = UsersTransactionsDatabase.getInstance(application).usersDataDao
        val viewModelFactory = UsersViewModelFactory(dataSource, application)

        users_listViewModel = ViewModelProvider(
            this, viewModelFactory).get(UsersViewModel::class.java)
        runBlocking {

            usersList = users_listViewModel.getAllUsersFromDataBase()

        }
        val adapter = UserListAdapter(usersList, UsersListListner {
            users_listViewModel.onUserClicked(it)
        })
        binding.setLifecycleOwner(this)
        binding.recyclerusers.let {
            it.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            it.adapter = adapter
            it.setHasFixedSize(true)
        }
        users_listViewModel.navigateToUsersDetail.observe(viewLifecycleOwner, Observer {

            it.let {
                if (it != null) {
                    user = it
                }

                if(findNavController().currentDestination?.id==R.id.usersListFragment){
                    val action = UsersListFragmentDirections
                        .actionUsersListFragmentToUsersDetailFragmnet(user)
                    this.findNavController()?.navigate(action)
                    users_listViewModel.onUserDetailNavigated()

                }
            }
        })
 return binding.root
    }

}