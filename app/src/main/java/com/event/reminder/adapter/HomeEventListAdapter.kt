package com.event.reminder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.event.reminder.R
import com.event.reminder.adapter.HomeEventListAdapter.NormalEventViewHolder
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.constant.EventsType
import com.event.reminder.data.model.response.HomeEventDetails
import com.event.reminder.databinding.ImportantEventTypeBinding
import com.event.reminder.databinding.NormalEventTypeBinding

/**
 * This Adapter class provide a binding from an app-specific data [HomeEventDetails] set to
 * views [R.layout.item_normal_event] that are displayed within a [RecyclerView].
 *
 * @param <VH> A class [NormalEventViewHolder] which extends ViewHolder that will be used by this adapter.
 * @author Shubham Chauhan
 */
class HomeEventListAdapter(
    private var homeEventDetailsList: ArrayList<HomeEventDetails>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }

        when (viewType) {

            EventsType.NORMAL -> {
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
            EventsType.IMPORTANT -> {
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
            EventsType.NORMAL -> EventsType.NORMAL
            EventsType.IMPORTANT -> EventsType.IMPORTANT
            EventsType.ALARM_TYPE -> EventsType.ALARM_TYPE
            else -> ErrorConstant.INVALID_NUMBER
        }
    }

    override fun onBindViewHolder(
        viewholder: RecyclerView.ViewHolder,
        position: Int
    ) {
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