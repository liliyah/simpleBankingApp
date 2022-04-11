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
import androidx.recyclerview.widget.LinearLayoutManager
import banksystems.bank.bankapp.banking.Adapters.TransactionsAdapter
import banksystems.bank.bankapp.banking.R
import banksystems.bank.bankapp.banking.ViewModels.TransactionViewModelFactory
import banksystems.bank.bankapp.banking.ViewModels.TransactionsViewModel
import banksystems.bank.bankapp.banking.ViewModels.UsersViewModel
import banksystems.bank.bankapp.banking.ViewModels.UsersViewModelFactory
import banksystems.bank.bankapp.banking.databinding.FragmentTransactionsBinding
import banksystems.bank.bankapp.banking.databinding.FragmentUsersListBinding
import banksystems.bank.bankapp.banking.room.UsersTransactionsDatabase

class TransactionsFragment : Fragment() {

    private lateinit var transactions_listViewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentTransactionsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_transactions, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = UsersTransactionsDatabase.getInstance(application).transactionDataDao
        val viewModelFactory = TransactionViewModelFactory(dataSource, application)

        transactions_listViewModel = ViewModelProvider(
            this, viewModelFactory).get(TransactionsViewModel::class.java)
        val adapter = TransactionsAdapter()
        binding.transactionsRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.transactionsRecyclerview.adapter= adapter
//        binding.transactionsRecyclerview.setHasFixedSize(true)
        transactions_listViewModel.TransactionLists.observe(viewLifecycleOwner, Observer {
            Log.d("submittedlist", "onCreateView:${it.toString()} ")
adapter.submitList(it)

        })
        binding.setLifecycleOwner(this)

        return binding.root
    }

}