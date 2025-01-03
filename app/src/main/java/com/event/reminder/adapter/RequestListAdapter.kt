package com.event.reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.event.reminder.R
import com.event.reminder.adapter.RequestListAdapter.RequestDetailsReceivedViewHolder
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.constant.RequestType
import com.event.reminder.data.model.response.FriendRequestDetails
import com.event.reminder.databinding.RequestListReceivedBinding
import com.event.reminder.databinding.RequestListSentBinding

/**
 * This Adapter class provide a binding from an app-specific data [FriendRequestDetails] set to
 * views [R.layout.item_request_received] that are displayed within a [RecyclerView].
 *
 * @param <VH> A class [RequestDetailsReceivedViewHolder] which extends ViewHolder that will be used by this adapter.
 * @author Shubham Chauhan
 */
class RequestListAdapter(
    private var requestList: ArrayList<FriendRequestDetails>?,
    private var iRequestListAdapterCallBack: IRequestListAdapterCallBack
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        when (viewType) {

            RequestType.REQUEST_TYPE_RECEIVED -> {
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
            RequestType.REQUEST_TYPE_SENT -> {
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
            RequestType.REQUEST_TYPE_RECEIVED -> RequestType.REQUEST_TYPE_RECEIVED
            RequestType.REQUEST_TYPE_SENT -> RequestType.REQUEST_TYPE_SENT
            else -> ErrorConstant.INVALID_NUMBER
        }
    }

    override fun onBindViewHolder(
        viewholder: RecyclerView.ViewHolder,
        position: Int
    ) {

        when (viewholder) {
            is RequestDetailsReceivedViewHolder -> {
                viewholder.binding?.friendDetail = requestList?.get(position)
                viewholder.binding?.ivProfile?.setOnClickListener {
                    iRequestListAdapterCallBack.onProfileClick(position)
                }
            }
            is RequestDetailsSentViewHolder -> {
                viewholder.binding?.friendDetail = requestList?.get(position)
                viewholder.binding?.ivProfile?.setOnClickListener {
                    iRequestListAdapterCallBack.onProfileClick(position)
                }
            }
        }
    }

    fun getFriendRequestList(): ArrayList<FriendRequestDetails>? {
        return requestList
    }

    fun setFriendRequestList(requestList: ArrayList<FriendRequestDetails>?) {
        this.requestList = requestList
    }

    inner class RequestDetailsReceivedViewHolder(val binding: RequestListReceivedBinding?) :
        RecyclerView.ViewHolder(binding?.root!!)

    inner class RequestDetailsSentViewHolder(val binding: RequestListSentBinding?) :
        RecyclerView.ViewHolder(binding?.root!!)

    interface IRequestListAdapterCallBack {
        fun onProfileClick(position: Int)
    }
}