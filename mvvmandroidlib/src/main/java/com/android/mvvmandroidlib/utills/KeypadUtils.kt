package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Fragment.showSoftKeyboard() {
    view?.let { activity?.showSoftKeyboard(it) }
}

fun Activity.showSoftKeyboard() {
    showSoftKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Fragment.hideSoftKeyboard() {
    view?.let { activity?.hideSoftKeyboard(it) }
}

fun Activity.hideSoftKeyboard() {
    hideSoftKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}
