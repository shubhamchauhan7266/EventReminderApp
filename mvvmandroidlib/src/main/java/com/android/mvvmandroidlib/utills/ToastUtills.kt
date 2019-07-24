package com.android.mvvmandroidlib.utills

import android.support.annotation.StringRes
import android.widget.Toast
import com.android.mvvmandroidlib.BaseApplication

object ToastUtills {

    fun showMessage(@StringRes message: Int) {

        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_LONG).show()
    }
}
