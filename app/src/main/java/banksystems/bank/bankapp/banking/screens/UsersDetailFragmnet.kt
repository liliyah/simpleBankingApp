package banksystems.bank.bankapp.banking.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import banksystems.bank.bankapp.banking.R
import banksystems.bank.bankapp.banking.databinding.FragmentUsersDetailFragmnetBinding
import banksystems.bank.bankapp.banking.databinding.FragmentUsersListBinding


class UsersDetailFragmnet : Fragment() {
    private val args by navArgs<UsersDetailFragmnetArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentUsersDetailFragmnetBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_users_detail_fragmnet, container, false)
        binding.customerName.text = args.user.user_name
        binding.accountNumber.text=args.user.account_number.toString()
        binding.customerEmail.text=args.user.user_email
        binding.currentBalance.text=args.user.current_balance.toString()


// send first customer name in a bundle to the fragmnet dialogue
        binding.buttonTransaction.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("username",args.user.user_name)
var dialogue = TransactionFragment()
            dialogue.arguments =bundle
            dialogue.show(requireFragmentManager()!!,"customdialoge")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


         val navController = Navigation.findNavController(view);


        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
// handle the back navigations this way to avoid getting redirected to the same fragment beacuse of the live data

                navController.popBackStack(R.id.homeScreenFragment, false);

            }
        }
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), onBackPressedCallback)

    }

}