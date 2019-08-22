package com.event.reminder.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.event.reminder.R
import com.event.reminder.data.model.response.NotificationDetailsModel
import com.event.reminder.databinding.NotificationListAdapterBinding

class NotificationListAdapter(
    private val context: Context,
    private val notificationList: ArrayList<NotificationDetailsModel>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: NotificationListAdapterBinding? =
            layoutInflater?.let { DataBindingUtil.inflate(it, R.layout.item_notification_details, parent, false) }
        return NotificationDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {

        val notificationDetailsViewHolder: NotificationDetailsViewHolder = viewholder as NotificationDetailsViewHolder
//        friendDetailsViewHolder.binding!!.ivProfile
    }

    inner class NotificationDetailsViewHolder(val binding: NotificationListAdapterBinding?) :
        RecyclerView.ViewHolder(binding!!.root)
}