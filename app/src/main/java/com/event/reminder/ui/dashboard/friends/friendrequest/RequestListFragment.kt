package com.event.reminder.ui.dashboard.friends.friendrequest


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.RequestListAdapter
import com.event.reminder.databinding.RequestListFragmentBinding
import com.event.reminder.enum.RequestType
import com.event.reminder.ui.ViewModelFactory

class RequestListFragment : BaseFragment<RequestListFragmentBinding, RequestListViewModel>() {
    private var requestType: Int = RequestType.REQUEST_TYPE_RECEIVED.ordinal

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        initializeAdapter()

        viewModel.getFriendRequestDetailsApiResult().observe(this@RequestListFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val friendRequestDetails = result.success
                    if (friendRequestDetails?.success == true) {

                        val adapter: RequestListAdapter =
                            binding.rvRequestList.adapter as RequestListAdapter
                        adapter.setFriendRequestList(friendRequestDetails.friendDetailsList)
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
        binding.rvRequestList.layoutManager = LinearLayoutManager(activity)
        binding.rvRequestList.itemAnimator = DefaultItemAnimator()
        binding.rvRequestList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvRequestList.adapter = RequestListAdapter(null)
    }

    override fun getObservableViewModel(): RequestListViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(RequestListViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): RequestListFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_request_list, container, false
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            requestType = it.getInt(ARG_REQUEST_TYPE, RequestType.REQUEST_TYPE_RECEIVED.ordinal)
        }
    }

    companion object {
        private const val ARG_REQUEST_TYPE: String = "RequestType"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RequestListFragment.
         */
        @JvmStatic
        fun newInstance(requestType: Int) =
            RequestListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_REQUEST_TYPE, requestType)
                }
            }
    }
}
