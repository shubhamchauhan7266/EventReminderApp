package com.event.reminder.ui.dashboard.friends.friendrequest


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.RequestListAdapter
import com.event.reminder.callback.INavigationCallback
import com.event.reminder.constant.BundleArgsConstant
import com.event.reminder.constant.RequestType
import com.event.reminder.databinding.RequestListFragmentBinding
import com.event.reminder.enums.NavigationScreen
import com.event.reminder.ui.ViewModelFactory

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for request list screen.
 *
 * @author Shubham Chauhan
 */
class RequestListFragment : BaseFragment<RequestListFragmentBinding, RequestListViewModel>(),
    RequestListAdapter.IRequestListAdapterCallBack {
    private var requestType: Int = RequestType.REQUEST_TYPE_RECEIVED
    private var iNavigationCallback: INavigationCallback? = null

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        arguments?.let {
            requestType = it.getInt(ARG_REQUEST_TYPE, RequestType.REQUEST_TYPE_RECEIVED)
        }
        initializeAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        iNavigationCallback = context as INavigationCallback
    }

    /**
     * Method is used to initialize adapter list
     */
    private fun initializeAdapter() {
        binding.rvRequestList.layoutManager =
            LinearLayoutManager(activity)
        binding.rvRequestList.itemAnimator = DefaultItemAnimator()
        binding.rvRequestList.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.rvRequestList.adapter = RequestListAdapter(null, this@RequestListFragment)
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

    /**
     * Navigate to profile screen by clicking on profile.
     * @param position
     */
    override fun onProfileClick(position: Int) {
        val adapter: RequestListAdapter =
            binding.rvRequestList.adapter as RequestListAdapter
        val bundle = Bundle()
        bundle.putBoolean(BundleArgsConstant.IS_FRIEND_PROFILE, true)
        bundle.putString(
            BundleArgsConstant.FRIEND_ID,
            adapter.getFriendRequestList()?.get(position)?.userId
        )
        iNavigationCallback?.navigateTo(NavigationScreen.REQUEST_LIST_TO_PROFILE_SCREEN, bundle)
    }
}
