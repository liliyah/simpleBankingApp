package banksystems.bank.bankapp.banking.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.findFragment
import banksystems.bank.bankapp.banking.R

class UsersArrayAdapter(context: Context,nameslist:List<String>) :ArrayAdapter<String>(context,0,nameslist) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }
    private  fun initView(position: Int, convertView: View?, parent: ViewGroup):View{
val name = getItem(position)
val view = LayoutInflater.from(context).inflate(R.layout.username_item,parent,false)
        view.findViewById<TextView>(R.id.userDialoguename).text= name
        return view
    }
}