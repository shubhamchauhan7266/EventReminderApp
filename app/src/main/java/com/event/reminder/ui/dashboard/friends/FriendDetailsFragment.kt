package com.event.reminder.ui.dashboard.friends

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.mvvmandroidlib.adapter.ViewPagerAdapter
import com.android.mvvmandroidlib.animation.ZoomOutPageTransformer
import com.android.mvvmandroidlib.data.FragmentModel
import com.android.mvvmandroidlib.listener.ViewPagerPageChangeListener
import com.android.mvvmandroidlib.ui.BaseFragment
import com.android.mvvmandroidlib.utills.ContextUtils
import com.event.reminder.R
import com.event.reminder.databinding.FriendDetailsFragmentBinding
import com.event.reminder.ui.ViewModelFactory

class FriendDetailsFragment : BaseFragment<FriendDetailsFragmentBinding, FriendDetailsViewModel>() {

    override fun onCreateViewBinding() {
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
                fragment = RequestListFragment.newInstance()
            )
        )
        fragmentModelList.add(
            FragmentModel(
                title = getString(R.string.request_sent),
                fragment = RequestListFragment.newInstance()
            )
        )

        if (ContextUtils.isActivityDestroyed(activity)) {
            return
        }
        binding.viewPager.adapter = ViewPagerAdapter(activity!!.supportFragmentManager, fragmentModelList)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.setPageTransformer(true, ZoomOutPageTransformer())
        binding.viewPager.addOnPageChangeListener(object : ViewPagerPageChangeListener() {
            override fun onPageSelected(position: Int) {

            }
        })
    }

    override fun getObservableViewModel(): FriendDetailsViewModel {
        return ViewModelProviders.of(this, ViewModelFactory())
            .get(FriendDetailsViewModel::class.java)
    }

    override fun getViewDataBinding(inflater: LayoutInflater, container: ViewGroup?): FriendDetailsFragmentBinding {
        return DataBindingUtil.inflate(
            inflater, R.layout.fragment_friend_details, container, false
        )
    }
}
