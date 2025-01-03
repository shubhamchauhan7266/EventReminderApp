package com.android.mvvmandroidlib.listener

import androidx.viewpager.widget.ViewPager

/**
 * This listener class is used to provide callback onPageSelected() for ViewPager.
 *
 * @author Shubham Chauhan
 */
abstract class ViewPagerPageChangeListener :
    ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
}