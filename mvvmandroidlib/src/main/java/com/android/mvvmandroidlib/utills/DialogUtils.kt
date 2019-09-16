package com.android.mvvmandroidlib.utills

import android.app.AlertDialog
import android.content.Context
import com.android.mvvmandroidlib.R

/**
 * This util class is used to show different type of dialog.
 * It will provide dialog to show some alert or to ask user for some confirmation.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object DialogUtils {

    private const val INVALID_RESOURCE_ID: Int = -1

    /**
     * Method is used to show dialog.
     *
     * @param context
     * @param title
     * @param message
     * @param okText
     * @param cancelText
     * @param callback
     * @param onlyOK true if you want only ok or +ve button else set false.
     */
    fun showDialog(
        context: Context,
        title: Int,
        message: Int,
        okText: Int,
        cancelText: Int,
        callback: IDialogUtilsCallback?,
        onlyOK: Boolean = false
    ) {

        if (ContextUtils.isContextNull(context)) {
            return
        }
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
        builder.setCancelable(true)
        if (title != INVALID_RESOURCE_ID)
            builder.setTitle(title)

        builder.setPositiveButton(
            okText
        ) { dialog, _ ->
            dialog.cancel()
            callback?.onOK()
        }

        if (!onlyOK) {
            builder.setNegativeButton(
                cancelText
            ) { dialog, _ ->
                dialog.cancel()
                callback?.onCancel()
            }
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    /**
     * Method is used to show dialog.
     *
     * @param context
     * @param message
     * @param okText
     * @param cancelText
     * @param callback
     */
    fun showDialog(
        context: Context,
        message: Int,
        okText: Int,
        cancelText: Int,
        callback: IDialogUtilsCallback?
    ) {
        showDialog(context, INVALID_RESOURCE_ID, message, okText, cancelText, callback, false)
    }

    /**
     * Method is used to show dialog.
     *
     * @param context
     * @param title
     * @param message
     * @param callback
     */
    fun showDialog(
        context: Context,
        title: Int,
        message: Int,
        callback: IDialogUtilsCallback?
    ) {
        showDialog(
            context,
            title,
            message,
            R.string.ok,
            R.string.cancel,
            callback,
            true
        )
    }

    /**
     * Method is used to show dialog.
     *
     * @param context
     * @param message
     * @param callback
     */
    fun showDialog(
        context: Context,
        message: Int,
        callback: IDialogUtilsCallback?
    ) {
        showDialog(context, INVALID_RESOURCE_ID, message, callback)
    }

    /**
     * Method is used to show dialog.
     *
     * @param context
     * @param title
     * @param message
     */
    fun showDialog(
        context: Context,
        title: Int,
        message: Int
    ) {
        showDialog(context, title, message, null)
    }

    /**
     * Method is used to show dialog.
     *
     * @param context
     * @param message
     */
    fun showDialog(
        context: Context,
        message: Int
    ) {
        showDialog(context, INVALID_RESOURCE_ID, message)
    }

    /**
     * Callback for ok and cancel.
     */
    interface IDialogUtilsCallback {
        fun onOK()
        fun onCancel() {
        }
    }
}
