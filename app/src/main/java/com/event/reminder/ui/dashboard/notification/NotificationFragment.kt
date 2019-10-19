package com.event.reminder.ui.dashboard.notification


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.ToastUtils
import com.event.reminder.R
import com.event.reminder.adapter.NotificationListAdapter
import com.event.reminder.data.model.response.NotificationDetails
import com.event.reminder.data.model.response.NotificationDetailsModel
import com.event.reminder.databinding.NotificationFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class NotificationFragment : BaseFragment<NotificationFragmentBinding, NotificationViewModel>() {

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        val notificationDetailsList: ArrayList<NotificationDetails> = ArrayList()
        notificationDetailsList.add(
            NotificationDetails(
                title = "Friend Request Accepted",
                description = "Your friend request has been accepted by raghav Singh"
            )
        )
        notificationDetailsList.add(
            NotificationDetails(
                title = "Friend Request Sent",
                description = "You have sent request to Vaibhav padalia"
            )
        )
        notificationDetailsList.add(
            NotificationDetails(
                title = "Friend Request Received",
                description = "You have receive friend request from Shubham Chauhan."
            )
        )

        viewModel.getNotificationDetailsApiResult().observe(this@NotificationFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val userDetails = result.success
                    if (userDetails?.success == true) {

                        // TODO set adapter here.
                    } else {
                        result.success!!.errorMessage?.let { it1 ->
                            ToastUtils.showMessage(
                                activity?.application!!,
                                it1
                            )
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { it1 ->
                        ToastUtils.showMessage(
                            activity?.application!!,
                            it1
                        )
                    }
                }
            }
        })


        binding.rvNotificationList.layoutManager = LinearLayoutManager(activity)
        binding.rvNotificationList.itemAnimator = DefaultItemAnimator()
        binding.rvNotificationList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvNotificationList.adapter = NotificationListAdapter(activity!!, notificationDetailsList)
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
