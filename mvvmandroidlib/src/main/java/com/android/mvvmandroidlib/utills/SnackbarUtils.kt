package com.android.mvvmandroidlib.utills

import android.view.View
import androidx.annotation.StringRes
import com.android.mvvmandroidlib.BuildConfig
import com.google.android.material.snackbar.Snackbar

/**
 * This util class is used to show SnackBar.
 * It will provide snake bar to show some message.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object SnackbarUtils {

    /**
     * Method is used to show message in snake bar
     *
     * @param view view
     * @param message message @StringRes
     * @param isShowOnlyInDebug true if show only in debug mode other wise set false. It is by default false.
     */
    fun showMessage(view: View, @StringRes message: Int, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Method is used to show message in snake bar
     *
     * @param view view
     * @param message message
     * @param isShowOnlyInDebug true if show only in debug mode other wise set false. It is by default false.
     */
    fun showMessage(view: View, message: String, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
