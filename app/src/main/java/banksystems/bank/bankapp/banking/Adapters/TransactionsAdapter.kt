package banksystems.bank.bankapp.banking.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import banksystems.bank.bankapp.banking.databinding.RecyclerTransactionBinding
import banksystems.bank.bankapp.banking.models.Transaction

class TransactionsAdapter : ListAdapter<Transaction, TransactionsAdapter.ViewHolder>(TransactionsDiffCallback()) {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TransactionsAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: TransactionsAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: RecyclerTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) {

            binding.transaction = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerTransactionBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}



class TransactionsDiffCallback : DiffUtil.ItemCallback<Transaction>() {

    override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem.transaction_id == newItem.transaction_id
    }


    override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
        return oldItem == newItem
    }


}