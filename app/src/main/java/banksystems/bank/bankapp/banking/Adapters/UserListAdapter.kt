package banksystems.bank.bankapp.banking.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import banksystems.bank.bankapp.banking.databinding.RecyclerUserBinding
import banksystems.bank.bankapp.banking.models.User
import banksystems.bank.bankapp.banking.utils.UsersListListner

class UserListAdapter(private  val usersList :List<User>,val clickListener: UsersListListner): RecyclerView.Adapter<UserListAdapter.UsersViewHolder>() {


    private lateinit var binding: RecyclerUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):UserListAdapter.UsersViewHolder{
        binding = RecyclerUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListAdapter.UsersViewHolder, position: Int) {
val userItem = usersList[position]
        holder.bind(userItem,clickListener)

    }

    override fun getItemCount(): Int {
return usersList.size

    }
    inner  class  UsersViewHolder (private val binding:RecyclerUserBinding):RecyclerView.ViewHolder(binding.root){

fun bind(item: User, clickListener: UsersListListner){
    binding.user = item
    binding.clickListener= clickListener

}

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}