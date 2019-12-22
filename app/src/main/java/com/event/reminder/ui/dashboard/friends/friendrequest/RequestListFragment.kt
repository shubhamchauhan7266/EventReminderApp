package com.event.reminder.ui.dashboard.friends.friendrequest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.RequestListAdapter
import com.event.reminder.constant.RequestType
import com.event.reminder.databinding.RequestListFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class RequestListFragment : BaseFragment<RequestListFragmentBinding, RequestListViewModel>() {
    private var requestType: Int = RequestType.REQUEST_TYPE_RECEIVED

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        arguments?.let {
            requestType = it.getInt(ARG_REQUEST_TYPE, RequestType.REQUEST_TYPE_RECEIVED)
        }
        initializeAdapter()
    }

    /**
     * Method is used to initialize adapter list
     */
    private fun initializeAdapter() {
        binding.rvRequestList.layoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(activity)
        binding.rvRequestList.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        binding.rvRequestList.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                activity,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )
        binding.rvRequestList.adapter = RequestListAdapter(null)
    }

    override fun setInitialData() {
        super.setInitialData()

        viewModel.getFriendRequestDetailsApiResult(requestType)
            .observe(this@RequestListFragment, Observer {
            val result = it ?: return@Observer

            when {
                result.success != null -> {

                    val friendRequestDetails = result.success
                    if (friendRequestDetails?.success == true) {

                        val adapter: RequestListAdapter =
                            binding.rvRequestList.adapter as RequestListAdapter
                        adapter.setFriendRequestList(friendRequestDetails.friendRequestDetailsList)
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

    override fun getObservableViewModel(): RequestListViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(RequestListViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): RequestListFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_request_list, container, false
        )
    }

    companion object {
        private const val ARG_REQUEST_TYPE: String = "RequestType"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param requestType Parameter 1.
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
