package com.android.mvvmandroidlib.data

import android.support.v4.app.Fragment

/**
 * This model class is used by ViewPagerAdapter.
 *
 * @param title title of fragment
 * @param fragment fragment instance
 *
 * @author Shubham Chauhan
 */
data class FragmentModel(
    val title: String = "",
    val fragment: Fragment
)