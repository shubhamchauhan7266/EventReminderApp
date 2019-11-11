package com.event.reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.event.reminder.R
import com.event.reminder.data.model.response.FriendDetails
import com.event.reminder.databinding.FriendListAdapterBinding


class FriendListAdapter(
    private var friendList: ArrayList<FriendDetails>?
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): androidx.recyclerview.widget.RecyclerView.ViewHolder {

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
        return friendList?.size ?: 0
    }

    override fun onBindViewHolder(
        viewholder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        position: Int
    ) {

        val friendDetailsViewHolder: FriendDetailsViewHolder = viewholder as FriendDetailsViewHolder
        friendDetailsViewHolder.binding!!.friendDetail = friendList?.get(position)
    }

    fun getFriendDetailsList(): ArrayList<FriendDetails>? {
        return friendList
    }

    fun setFriendDetailsList(friendList: ArrayList<FriendDetails>?) {
        this.friendList = friendList
    }

    inner class FriendDetailsViewHolder(val binding: FriendListAdapterBinding?) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding!!.root)
}