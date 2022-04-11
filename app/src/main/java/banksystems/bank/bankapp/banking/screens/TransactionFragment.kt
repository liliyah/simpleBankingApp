package banksystems.bank.bankapp.banking.screens

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import banksystems.bank.bankapp.banking.Adapters.UsersArrayAdapter
import banksystems.bank.bankapp.banking.R
import banksystems.bank.bankapp.banking.ViewModels.TransactionViewModelFactory
import banksystems.bank.bankapp.banking.ViewModels.TransactionsViewModel
import banksystems.bank.bankapp.banking.ViewModels.UsersViewModel
import banksystems.bank.bankapp.banking.ViewModels.UsersViewModelFactory
import banksystems.bank.bankapp.banking.databinding.FragmentTransactionBinding
import banksystems.bank.bankapp.banking.models.Transaction
import banksystems.bank.bankapp.banking.models.User
import banksystems.bank.bankapp.banking.room.UsersTransactionsDatabase
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class TransactionFragment : DialogFragment() {
    private var username: String = ""
    private lateinit var users_listViewModel: UsersViewModel
    private lateinit var transaction_listViewModel: TransactionsViewModel
    private var selecteditem: String = ""
    private lateinit var transaction: Transaction
    private lateinit var binding: FragmentTransactionBinding


    private lateinit var usersList: List<User>
    private var nameslist: List<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = arguments?.getString("username").toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val application = requireNotNull(this.activity).application

         binding=
            DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                R.layout.fragment_transaction,
                null,
                false)
        usersList = ArrayList<User>()
        val dataSource = UsersTransactionsDatabase.getInstance(application).usersDataDao
        val viewModelFactory = UsersViewModelFactory(dataSource, application)

        users_listViewModel = ViewModelProvider(
            this, viewModelFactory).get(UsersViewModel::class.java)
        runBlocking {

            usersList = users_listViewModel.getAllUsersFromDataBase()

        }
        nameslist = getUseresNames(usersList)

        val adapter = UsersArrayAdapter(requireContext(), nameslist)
        binding.amountTransferred.adapter = adapter

        binding.amountTransferred.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selecteditem = parent!!.getItemAtPosition(position).toString()


                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }


            }


        Log.d("nameslist", "onCreateDialog: ${nameslist.size}")
        binding.performtransaction.setOnClickListener {
            val fromUser = username
            val touser = selecteditem
            var theDate = LocalDateTime.now().toString()
            val amountTransferred = binding.amountTobetransferred.text.toString()

            if (TextUtils.isEmpty(amountTransferred)) {
                binding.amountTobetransferred.error = "please enter amount to be transferred"
                Toast.makeText(requireContext(), "cannot make transaction", Toast.LENGTH_SHORT)
                    .show()
            }else{

                if (fromUser != "" && touser != "" && amountTransferred != "") {
                    Log.d("data", "onCreateDialog:$fromUser $touser $amountTransferred $theDate")
                    transaction = Transaction(fromUser, touser, amountTransferred,
                        theDate)
                    val dataSource =
                        UsersTransactionsDatabase.getInstance(application).transactionDataDao
                    val transactionviewModelFactory =
                        TransactionViewModelFactory(dataSource, application)

                    transaction_listViewModel = ViewModelProvider(
                        this, transactionviewModelFactory).get(TransactionsViewModel::class.java)
                    transaction_listViewModel.insertTransactionIndatabase(transaction)
                    Toast.makeText(requireContext(), "Transaction Sucessful", Toast.LENGTH_SHORT)
                        .show()
                }else {

                    Toast.makeText(requireContext(), "Transaction  not Sucessful", Toast.LENGTH_SHORT)
                        .show()

                }







            }











        }

            binding.imageExit.setOnClickListener {
                dismiss()

            }
            return AlertDialog.Builder(activity, R.style.DatePickerTheme)
                .setView(binding.root)
                .create()
        }

        override fun onStart() {
            super.onStart()
            val dialog = dialog
            if (dialog != null) {
                dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
            }
        }

        fun getUseresNames(usersList: List<User>): List<String> {
            return usersList.map { it.user_name }.toList()
        }
    }
