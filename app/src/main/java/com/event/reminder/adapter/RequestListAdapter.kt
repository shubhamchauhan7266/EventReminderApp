package com.event.reminder.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.event.reminder.R
import com.event.reminder.constant.AppConstant
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.data.model.response.FriendRequestDetails
import com.event.reminder.databinding.RequestListReceivedBinding
import com.event.reminder.databinding.RequestListSentBinding

class RequestListAdapter(
    private var requestList: ArrayList<FriendRequestDetails>?
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        when (viewType) {

            AppConstant.REQUEST_TYPE_RECEIVED -> {
                val binding: RequestListReceivedBinding? =
                    layoutInflater?.let {
                        DataBindingUtil.inflate(
                            it,
                            R.layout.item_request_received,
                            parent,
                            false
                        )
                    }
                return RequestDetailsReceivedViewHolder(binding)
            }
            AppConstant.REQUEST_TYPE_SENT -> {
                val binding: RequestListSentBinding? =
                    layoutInflater?.let {
                        DataBindingUtil.inflate(
                            it,
                            R.layout.item_request_sent,
                            parent,
                            false
                        )
                    }
                return RequestDetailsSentViewHolder(binding)
            }
            else -> {
                val binding: RequestListReceivedBinding? =
                    layoutInflater?.let {
                        DataBindingUtil.inflate(
                            it,
                            R.layout.item_request_received,
                            parent,
                            false
                        )
                    }
                return RequestDetailsReceivedViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return requestList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (requestList?.get(position)?.requestType) {
            AppConstant.REQUEST_TYPE_RECEIVED -> AppConstant.REQUEST_TYPE_RECEIVED
            AppConstant.REQUEST_TYPE_SENT -> AppConstant.REQUEST_TYPE_SENT
            else -> ErrorConstant.INVALID_NUMBER
        }
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {

        when (viewholder) {
            is RequestDetailsReceivedViewHolder -> {
                viewholder.binding!!.friendDetail = requestList?.get(position)
            }
            is RequestDetailsSentViewHolder -> {
                viewholder.binding!!.friendDetail = requestList?.get(position)
            }
        }
    }

    fun getFriendRequestList() : ArrayList<FriendRequestDetails>?{
        return requestList
    }

    fun setFriendRequestList(requestList: ArrayList<FriendRequestDetails>?) {
        this.requestList = requestList
    }

    inner class RequestDetailsReceivedViewHolder(val binding: RequestListReceivedBinding?) :
        RecyclerView.ViewHolder(binding!!.root)

    inner class RequestDetailsSentViewHolder(val binding: RequestListSentBinding?) :
        RecyclerView.ViewHolder(binding!!.root)
}