package com.event.reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.event.reminder.R
import com.event.reminder.adapter.FriendListAdapter.FriendDetailsViewHolder
import com.event.reminder.data.model.response.FriendDetails
import com.event.reminder.databinding.FriendListAdapterBinding

/**
 * This Adapter class provide a binding from an app-specific data [FriendDetails] set to
 * views [R.layout.item_friend_details] that are displayed within a [RecyclerView].
 *
 * @param <VH> A class [FriendDetailsViewHolder] which extends ViewHolder that will be used by this adapter.
 * @author Shubham Chauhan
 */
class FriendListAdapter(
    private var friendList: ArrayList<FriendDetails>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

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
        viewholder: RecyclerView.ViewHolder,
        position: Int
    ) {

        val friendDetailsViewHolder: FriendDetailsViewHolder = viewholder as FriendDetailsViewHolder
        friendDetailsViewHolder.binding?.friendDetail = friendList?.get(position)
    }

    fun getFriendDetailsList(): ArrayList<FriendDetails>? {
        return friendList
    }

    fun setFriendDetailsList(friendList: ArrayList<FriendDetails>?) {
        this.friendList = friendList
    }

    inner class FriendDetailsViewHolder(val binding: FriendListAdapterBinding?) :
        RecyclerView.ViewHolder(binding?.root!!)
}