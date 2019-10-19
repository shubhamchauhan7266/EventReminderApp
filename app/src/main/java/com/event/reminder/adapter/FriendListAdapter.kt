package com.event.reminder.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.event.reminder.R
import com.event.reminder.data.model.response.FriendDetails
import com.event.reminder.databinding.FriendListAdapterBinding


class FriendListAdapter(
    private val context: Context,
    private val friendList: ArrayList<FriendDetails>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: FriendListAdapterBinding? =
            layoutInflater?.let {
                DataBindingUtil.inflate(
                    it,
                    R.layout.item_friend_details,
                    parent,
                    false
                )
            }
        return FriendDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {

        val friendDetailsViewHolder: FriendDetailsViewHolder = viewholder as FriendDetailsViewHolder
        friendDetailsViewHolder.binding!!.friendDetail = friendList[position]
    }

    inner class FriendDetailsViewHolder(val binding: FriendListAdapterBinding?) :
        RecyclerView.ViewHolder(binding!!.root)
}