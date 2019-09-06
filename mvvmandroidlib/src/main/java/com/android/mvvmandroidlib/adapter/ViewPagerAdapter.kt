package com.android.mvvmandroidlib.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.android.mvvmandroidlib.data.FragmentModel

/**
 * This adapter class is used by ViewPager to add fragment.
 *
 * @author Shubham Chauhan
 */
class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList: ArrayList<FragmentModel>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position].fragment
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentList[position].title
    }
}