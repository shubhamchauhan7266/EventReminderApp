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
import com.event.reminder.adapter.FriendListAdapter
import com.event.reminder.data.model.response.FriendDetailsModel
import com.event.reminder.databinding.FriendListFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class FriendListFragment : BaseFragment<FriendListFragmentBinding, FriendListViewModel>() {

    override fun onCreateViewBinding() {
        binding.viewModel = viewModel

        val friendDetailsList: ArrayList<FriendDetailsModel> = ArrayList()
        friendDetailsList.add(FriendDetailsModel(name = "Subham Chauhan", age = "25"))
        friendDetailsList.add(FriendDetailsModel(name = "Pulkit", age = "25"))
        friendDetailsList.add(FriendDetailsModel(name = "Raghav", age = "25"))

        binding.rvFriendList.layoutManager = LinearLayoutManager(activity)
        binding.rvFriendList.itemAnimator = DefaultItemAnimator()
        binding.rvFriendList.adapter = FriendListAdapter(activity!!, friendDetailsList)
    }

    override fun getObservableViewModel(): FriendListViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(FriendListViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): FriendListFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_friend_list, container, false
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
         * @return A new instance of fragment FriendDetailsFragment.
         */
        @JvmStatic
        fun newInstance() =
            FriendListFragment().apply {
                arguments = Bundle().apply {
                    //                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
