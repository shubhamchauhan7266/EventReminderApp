package com.android.mvvmandroidlib.utills

import android.app.AlertDialog
import android.content.Context
import com.android.mvvmandroidlib.R


object DialogUtils {

    private const val INVALID_RESOURCE_ID: Int = -1

    fun showDialog(
        context: Context,
        title: Int,
        message: Int,
        okText: Int,
        cancelText: Int,
        callback: IDialogUtilsCallback?,
        onlyOK: Boolean = false
    ) {

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

    fun showDialog(
        context: Context,
        message: Int,
        okText: Int,
        cancelText: Int,
        callback: IDialogUtilsCallback?
    ) {
        showDialog(context, INVALID_RESOURCE_ID, message, okText, cancelText, callback, false)
    }

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

    fun showDialog(
        context: Context,
        message: Int,
        callback: IDialogUtilsCallback?
    ) {
        showDialog(context, INVALID_RESOURCE_ID, message, callback)
    }

    fun showDialog(
        context: Context,
        title: Int,
        message: Int
    ) {
        showDialog(context, title, message, null)
    }

    fun showDialog(
        context: Context,
        message: Int
    ) {
        showDialog(context, INVALID_RESOURCE_ID, message)
    }

    interface IDialogUtilsCallback {
        fun onOK()
        fun onCancel() {

        }
    }
}
