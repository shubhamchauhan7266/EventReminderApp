package com.android.mvvmandroidlib.adapter

import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.mvvmandroidlib.data.FragmentModel
import com.android.mvvmandroidlib.utills.StringUtils

/**
 * This adapter class is used by ViewPager to add fragment.
 *
 * @author Shubham Chauhan
 */
class ViewPagerAdapter(
    fragmentManager: androidx.fragment.app.FragmentManager,
    private val fragmentList: ArrayList<FragmentModel>?
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return fragmentList?.get(position)?.fragment ?: androidx.fragment.app.Fragment()
    }

    override fun getCount(): Int {
        return fragmentList?.size ?: 0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList?.get(position)?.title ?: StringUtils.EMPTY
    }
}