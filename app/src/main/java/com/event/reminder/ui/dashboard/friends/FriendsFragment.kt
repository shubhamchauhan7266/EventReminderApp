package com.event.reminder.ui.dashboard.friends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.mvvmandroidlib.adapter.ViewPagerAdapter
import com.android.mvvmandroidlib.animation.ZoomOutPageTransformer
import com.android.mvvmandroidlib.data.FragmentModel
import com.android.mvvmandroidlib.listener.ViewPagerPageChangeListener
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.ContextUtils
import com.event.reminder.R
import com.event.reminder.constant.RequestType
import com.event.reminder.databinding.FriendsFragmentBinding
import com.event.reminder.ui.ViewModelFactory
import com.event.reminder.ui.dashboard.friends.friendrequest.RequestListFragment
import com.event.reminder.ui.dashboard.friends.friends.FriendListFragment

/**
 * This Fragment Class is used to provide layout and lifecycle callback to handle UI for friends section screen.
 *
 * @author Shubham Chauhan
 */
class FriendsFragment : BaseFragment<FriendsFragmentBinding, FriendsViewModel>() {

    override fun onCreateViewBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        val fragmentModelList: ArrayList<FragmentModel> = ArrayList()
        fragmentModelList.add(
            FragmentModel(
                title = getString(R.string.friends),
                fragment = FriendListFragment.newInstance()
            )
        )
        fragmentModelList.add(
            FragmentModel(
                title = getString(R.string.request_received),
                fragment = RequestListFragment.newInstance(RequestType.REQUEST_TYPE_RECEIVED)
            )
        )
        fragmentModelList.add(
            FragmentModel(
                title = getString(R.string.request_sent),
                fragment = RequestListFragment.newInstance(RequestType.REQUEST_TYPE_SENT)
            )
        )

        if (ContextUtils.isActivityDestroyed(activity)) {
            return
        }
        binding.viewPager.adapter =
            ViewPagerAdapter(activity?.supportFragmentManager!!, fragmentModelList)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        binding.viewPager.addOnPageChangeListener(object : ViewPagerPageChangeListener() {
            override fun onPageSelected(position: Int) {

            }
        })
    }

    override fun getObservableViewModel(): FriendsViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(FriendsViewModel::class.java)
    }

    override fun getViewDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FriendsFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_friends, container, false
        )
    }
}
