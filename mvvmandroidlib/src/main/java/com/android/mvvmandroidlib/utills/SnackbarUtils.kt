package com.android.mvvmandroidlib.utills

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View
import com.android.mvvmandroidlib.BuildConfig

object SnackbarUtils {

    fun showMessage(view: View, @StringRes message: Int, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun showMessage(view: View, message: String, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
