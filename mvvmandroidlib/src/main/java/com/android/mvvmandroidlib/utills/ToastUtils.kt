package com.android.mvvmandroidlib.utills

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import com.android.mvvmandroidlib.BuildConfig

object ToastUtils {

    fun showMessage(context: Context, @StringRes message: Int, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showMessage(context: Context, message: String, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}
