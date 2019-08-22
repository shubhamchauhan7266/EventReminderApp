package com.event.reminder.ui.dashboard.friends


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.ui.BaseFragment
import com.event.reminder.R
import com.event.reminder.adapter.RequestListAdapter
import com.event.reminder.data.model.response.FriendDetailsModel
import com.event.reminder.databinding.RequestListFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class RequestListFragment : BaseFragment<RequestListFragmentBinding, RequestListViewModel>() {
    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        val requestDetailsList: ArrayList<FriendDetailsModel> = ArrayList()
        requestDetailsList.add(FriendDetailsModel(name = "Subham Chauhan"))
        requestDetailsList.add(FriendDetailsModel(name = "Pulkit"))
        requestDetailsList.add(FriendDetailsModel(name = "Raghav"))

        binding.rvRequestList.layoutManager = LinearLayoutManager(activity)
        binding.rvRequestList.itemAnimator = DefaultItemAnimator()
        binding.rvRequestList.adapter = RequestListAdapter(activity!!, requestDetailsList)
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
            //            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RequestListFragment.
         */
        @JvmStatic
        fun newInstance() =
            RequestListFragment().apply {
                arguments = Bundle().apply {
                    //                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
