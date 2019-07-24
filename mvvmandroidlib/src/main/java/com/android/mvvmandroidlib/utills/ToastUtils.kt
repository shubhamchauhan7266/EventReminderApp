package com.android.mvvmandroidlib.utills

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

object ToastUtils {

    fun showMessage(context: Context, @StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
