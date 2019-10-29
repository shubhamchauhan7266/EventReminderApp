package com.event.reminder.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.event.reminder.R
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.data.model.response.HomeEventDetails
import com.event.reminder.databinding.ImportantEventTypeBinding
import com.event.reminder.databinding.NormalEventTypeBinding
import com.event.reminder.enum.EventsType

class HomeEventListAdapter(
    private var homeEventDetailsList: ArrayList<HomeEventDetails>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        when (viewType) {

            EventsType.NORMAL.ordinal -> {
                val binding: NormalEventTypeBinding? =
                    layoutInflater?.let {
                        DataBindingUtil.inflate(
                            it,
                            R.layout.item_normal_event,
                            parent,
                            false
                        )
                    }
                return NormalEventViewHolder(binding)
            }
            EventsType.IMPORTANT.ordinal -> {
                val binding: ImportantEventTypeBinding? =
                    layoutInflater?.let {
                        DataBindingUtil.inflate(
                            it,
                            R.layout.item_important_event,
                            parent,
                            false
                        )
                    }
                return ImportantEventViewHolder(binding)
            }
            else -> {
                val binding: NormalEventTypeBinding? =
                    layoutInflater?.let {
                        DataBindingUtil.inflate(
                            it,
                            R.layout.item_request_received,
                            parent,
                            false
                        )
                    }
                return NormalEventViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return homeEventDetailsList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (homeEventDetailsList?.get(position)?.eventType) {
            EventsType.NORMAL.ordinal -> EventsType.NORMAL.ordinal
            EventsType.IMPORTANT.ordinal -> EventsType.IMPORTANT.ordinal
            EventsType.ALARM_TYPE.ordinal -> EventsType.ALARM_TYPE.ordinal
            else -> ErrorConstant.INVALID_NUMBER
        }
    }

    override fun onBindViewHolder(viewholder: RecyclerView.ViewHolder, position: Int) {
        when (viewholder) {
            is NormalEventViewHolder -> {
                viewholder.binding!!.homeEventDetails = homeEventDetailsList?.get(position)
            }
            is ImportantEventViewHolder -> {
                viewholder.binding!!.homeEventDetails = homeEventDetailsList?.get(position)
            }
        }
    }

    fun getHomeEventDetailsList(): ArrayList<HomeEventDetails>? {
        return homeEventDetailsList
    }

    fun setHomeEventDetailsList(homeEventDetailsList: ArrayList<HomeEventDetails>?) {
        this.homeEventDetailsList = homeEventDetailsList
    }

    inner class NormalEventViewHolder(val binding: NormalEventTypeBinding?) :
        RecyclerView.ViewHolder(binding!!.root)

    inner class ImportantEventViewHolder(val binding: ImportantEventTypeBinding?) :
        RecyclerView.ViewHolder(binding!!.root)
}