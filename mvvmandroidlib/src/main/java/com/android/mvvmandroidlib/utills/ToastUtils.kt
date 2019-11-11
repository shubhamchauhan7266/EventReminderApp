package com.android.mvvmandroidlib.utills

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.android.mvvmandroidlib.BuildConfig

/**
 * This util class is used to show Toast.
 * It will provide toast to show some message.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object ToastUtils {

    /**
     * Method is used to show message in snake bar
     *
     * @param context context
     * @param message message @StringRes
     * @param isShowOnlyInDebug true if show only in debug mode other wise set false. It is by default false.
     */
    fun showMessage(context: Context, @StringRes message: Int, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Method is used to show message in snake bar
     *
     * @param context context
     * @param message message
     * @param isShowOnlyInDebug true if show only in debug mode other wise set false. It is by default false.
     */
    fun showMessage(context: Context, message: String, isShowOnlyInDebug: Boolean = false) {

        if (!isShowOnlyInDebug || BuildConfig.DEBUG)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}
