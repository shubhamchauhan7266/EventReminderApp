package com.event.reminder.ui.dashboard.notification


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.NotificationListAdapter
import com.event.reminder.data.model.response.NotificationDetailsModel
import com.event.reminder.databinding.NotificationFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class NotificationFragment : BaseFragment<NotificationFragmentBinding, NotificationViewModel>() {

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        val requestDetailsList: ArrayList<NotificationDetailsModel> = ArrayList()
        requestDetailsList.add(NotificationDetailsModel(title = "Friend Request Received"))
        requestDetailsList.add(NotificationDetailsModel(title = "Friend Request Received"))
        requestDetailsList.add(NotificationDetailsModel(title = "Friend Request Received"))

        binding.rvNotificationList.layoutManager = LinearLayoutManager(activity)
        binding.rvNotificationList.itemAnimator = DefaultItemAnimator()
        binding.rvNotificationList.adapter = NotificationListAdapter(activity!!, requestDetailsList)
    }

    override fun getObservableViewModel(): NotificationViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(NotificationViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): NotificationFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_notification, container, false
        )
    }
}
