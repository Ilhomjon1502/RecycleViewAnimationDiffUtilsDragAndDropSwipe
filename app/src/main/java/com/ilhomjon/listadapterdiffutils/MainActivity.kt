package com.ilhomjon.listadapterdiffutils

import Adapter.UserAdapter
import Adapter.UserOtherAdapter
import Models.User
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var userList: ArrayList<User>
    lateinit var userAdapter: UserAdapter

    lateinit var userOtherAdapter: UserOtherAdapter

    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userList = ArrayList()
//        userAdapter = UserAdapter()
//        userAdapter.submitList(userList)

        userOtherAdapter = UserOtherAdapter(this, userList)
        rv.adapter = userOtherAdapter

        val itemTouchHelper = object : ItemTouchHelper.Callback(){
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                userOtherAdapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                userOtherAdapter.onItemDismiss(viewHolder.adapterPosition)
            }
        }

        val itemTouch = ItemTouchHelper(itemTouchHelper)
        itemTouch.attachToRecyclerView(rv)

        btn.setOnClickListener {
            val user = User(count++, edt_userName.text.toString(), edt_password.text.toString())
            userList.add(user)
//            userAdapter.submitList(userList)
            userOtherAdapter.notifyDataSetChanged()
            rv.adapter = userOtherAdapter
        }

    }
}