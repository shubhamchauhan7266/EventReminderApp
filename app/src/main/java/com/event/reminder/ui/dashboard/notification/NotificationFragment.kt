package com.event.reminder.ui.dashboard.notification


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.NotificationListAdapter
import com.event.reminder.databinding.NotificationFragmentBinding
import com.event.reminder.ui.ViewModelFactory

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for notification screen.
 *
 * @author Shubham Chauhan
 */
class NotificationFragment : BaseFragment<NotificationFragmentBinding, NotificationViewModel>() {

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
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
                        result.success?.errorMessage?.let { error ->
                            viewModel.failedEventErrorMessage.sendEvent(error)
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
        binding.rvNotificationList.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(activity)
        binding.rvNotificationList.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        binding.rvNotificationList.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                activity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
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
