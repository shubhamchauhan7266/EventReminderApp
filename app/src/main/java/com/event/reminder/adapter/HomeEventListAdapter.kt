package com.event.reminder.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.event.reminder.data.model.response.HomeEventList

class HomeEventListAdapter(
    private var homeEventList: ArrayList<HomeEventList>?

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var layoutInflater: LayoutInflater? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return homeEventList?.size ?: 0
    }

//    override fun getItemViewType(position: Int): Int {
//        return when (requestList?.get(position)?.requestType) {
//            AppConstant.REQUEST_TYPE_RECEIVED -> AppConstant.REQUEST_TYPE_RECEIVED
//            AppConstant.REQUEST_TYPE_SENT -> AppConstant.REQUEST_TYPE_SENT
//            else -> ErrorConstant.INVALID_NUMBER
//        }
//
//        AppConstant.ccc.RTC.name
//    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getHomeEventList(): ArrayList<HomeEventList>? {
        return homeEventList
    }

    fun setHomeEventList(homeEventList: ArrayList<HomeEventList>?) {
        this.homeEventList = homeEventList
    }
}