package Adapter

import Models.User
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.listadapterdiffutils.R
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : ListAdapter<User, UserAdapter.VH>(MyDifUtils()) {

    inner class VH(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun onBind(user: User){
            itemView.txt_1.text = user.userName
            itemView.txt_2.text = user.password
        }
    }

    class MyDifUtils : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(getItem(position))
    }
}