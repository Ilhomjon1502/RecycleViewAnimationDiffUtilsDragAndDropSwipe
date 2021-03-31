package Adapter

import Models.User
import Utils.ItemTouchHelperAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.listadapterdiffutils.R
import kotlinx.android.synthetic.main.item_user.view.*
import java.util.*
import kotlin.collections.ArrayList

class UserOtherAdapter(val context: Context, val list: ArrayList<User>) : RecyclerView.Adapter<UserOtherAdapter.VH>(),
ItemTouchHelperAdapter{

    inner class VH(itemView:View) : RecyclerView.ViewHolder(itemView){

        fun onBInd(user: User){
            itemView.txt_1.text = user.userName
            itemView.txt_2.text = user.password

            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.anim1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBInd(list[position])
    }

    override fun getItemCount(): Int = list.size


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list, i, i+1)
            }
        }else{
            for (i in fromPosition until toPosition+1){
                Collections.swap(list, i, i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

}