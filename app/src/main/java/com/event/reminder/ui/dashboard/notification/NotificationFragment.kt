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
import com.event.reminder.databinding.NotificationFragmentBinding
import com.event.reminder.ui.ViewModelFactory
import java.util.*

class NotificationFragment : BaseFragment<NotificationFragmentBinding, NotificationViewModel>() {

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        initializeAdapter()

        viewModel.getNotificationDetailsApiResult().observe(this@NotificationFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val notificationDetails = result.success
                    if (notificationDetails?.success == true) {
                        val adapter: NotificationListAdapter =
                            binding.rvNotificationList.adapter as NotificationListAdapter
                        adapter.setNotificationList(notificationDetails.notificationDetailsList)
                        adapter.notifyDataSetChanged()
                    } else {
                        result.success!!.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)
                        }
                    }
                }
                result.errorMessage != null -> {
                    result.errorMessage?.let { error -> viewModel.failedEventErrorMessage.sendEvent(error)
                    }
                }
            }
        })
    }

    /**
     * Method is used to initialize adapter list
     */
    private fun initializeAdapter() {
        binding.rvNotificationList.layoutManager = LinearLayoutManager(activity)
        binding.rvNotificationList.itemAnimator = DefaultItemAnimator()
        binding.rvNotificationList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvNotificationList.adapter = NotificationListAdapter(null)
    }

    override fun getObservableViewModel(): NotificationViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(NotificationViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): NotificationFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_notification, container, false
        )
    }
}
