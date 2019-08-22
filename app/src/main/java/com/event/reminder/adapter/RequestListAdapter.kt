package com.event.reminder.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.event.reminder.R
import com.event.reminder.data.model.response.FriendDetailsModel
import com.event.reminder.databinding.RequestListAdapterBinding

class RequestListAdapter(private val context: Context, private val friendList: ArrayList<FriendDetailsModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: RequestListAdapterBinding? =
            layoutInflater?.let { DataBindingUtil.inflate(it, R.layout.item_request_details, parent, false) }
        return RequestDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {

        val requestDetailsViewHolder: RequestDetailsViewHolder = viewholder as RequestDetailsViewHolder
//        friendDetailsViewHolder.binding!!.ivProfile
    }

    inner class RequestDetailsViewHolder(val binding: RequestListAdapterBinding?) :
        RecyclerView.ViewHolder(binding!!.root)
}