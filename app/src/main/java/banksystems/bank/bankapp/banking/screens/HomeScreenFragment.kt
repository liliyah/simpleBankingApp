package banksystems.bank.bankapp.banking.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import banksystems.bank.bankapp.banking.R
import banksystems.bank.bankapp.banking.databinding.FragmentHomeScreenBinding


class HomeScreenFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentHomeScreenBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)
        binding.customersData.setOnClickListener {
            it.findNavController()
                .navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToUsersListFragment())

        }
        binding.transactionData.setOnClickListener {

            it.findNavController()
                .navigate(HomeScreenFragmentDirections.actionHomeScreenFragmentToTransactionsFragment())

        }
        return binding.root
    }
}