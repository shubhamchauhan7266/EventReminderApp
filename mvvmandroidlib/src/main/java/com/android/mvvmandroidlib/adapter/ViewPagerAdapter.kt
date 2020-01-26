package com.android.mvvmandroidlib.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.mvvmandroidlib.data.FragmentModel
import com.android.mvvmandroidlib.utills.StringUtils

/**
 * This adapter class is used by ViewPager to add fragment.
 *
 * @author Shubham Chauhan
 */
class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList: ArrayList<FragmentModel>?
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragmentList?.get(position)?.fragment ?: Fragment()
    }

    override fun getCount(): Int {
        return fragmentList?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList?.get(position)?.title ?: StringUtils.EMPTY
    }
}