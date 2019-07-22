package com.android.mvvmandroidlib.utills

import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.view.View

object SnackbarUtills{

    fun showMessage(view: View, @StringRes message: Int) {

        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}
