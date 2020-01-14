package com.event.reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.event.reminder.R
import com.event.reminder.adapter.NotificationListAdapter.NotificationDetailsViewHolder
import com.event.reminder.data.model.response.NotificationDetails
import com.event.reminder.databinding.NotificationListAdapterBinding

/**
 * This Adapter class provide a binding from an app-specific data [NotificationDetails] set to
 * views [R.layout.item_notification_details] that are displayed within a [RecyclerView].
 *
 * @param <VH> A class [NotificationDetailsViewHolder] which extends ViewHolder that will be used by this adapter.
 * @author Shubham Chauhan
 */
class NotificationListAdapter(
    private var notificationList: ArrayList<NotificationDetails>?
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
        val binding: NotificationListAdapterBinding? =
            layoutInflater?.let {
                DataBindingUtil.inflate(
                    it,
                    R.layout.item_notification_details,
                    parent,
                    false
                )
            }
        return NotificationDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notificationList?.size ?: 0
    }

    override fun onBindViewHolder(
        viewholder: RecyclerView.ViewHolder,
        position: Int
    ) {

        val notificationDetailsViewHolder: NotificationDetailsViewHolder =
            viewholder as NotificationDetailsViewHolder
        notificationDetailsViewHolder.binding?.notificationDetail = notificationList?.get(position)
    }

    fun getNotificationList(): ArrayList<NotificationDetails>? {
        return notificationList
    }

    fun setNotificationList(notificationList: ArrayList<NotificationDetails>?) {
        this.notificationList = notificationList
    }

    inner class NotificationDetailsViewHolder(val binding: NotificationListAdapterBinding?) :
        RecyclerView.ViewHolder(binding?.root!!)
}