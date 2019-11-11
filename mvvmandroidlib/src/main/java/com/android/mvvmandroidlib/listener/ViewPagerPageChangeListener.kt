package com.android.mvvmandroidlib.listener

/**
 * This listener class is used to provide callback onPageSelected() for ViewPager.
 *
 * @param onPageSelected callback
 *
 * @author Shubham Chauhan
 */
abstract class ViewPagerPageChangeListener() :
    androidx.viewpager.widget.ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }
}