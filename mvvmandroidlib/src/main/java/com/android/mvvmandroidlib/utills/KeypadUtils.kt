package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * This file is used to provide some methods related to keyboard.
 *
 * @author Shubham Chauhan
 */

/**
 * Method is used to show keyboard.
 *
 * @param view view
 */
fun Context.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

/**
 * Method is used to show keyboard.
 */
fun Fragment.showSoftKeyboard() {
    view?.let { activity?.showSoftKeyboard(it) }
}

/**
 * Method is used to show keyboard.
 */
fun Activity.showSoftKeyboard() {
    showSoftKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

/**
 * Method is used to hide keyboard.
 */
fun Fragment.hideSoftKeyboard() {
    view?.let { activity?.hideSoftKeyboard(it) }
}

/**
 * Method is used to hide keyboard.
 */
fun Activity.hideSoftKeyboard() {
    hideSoftKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

/**
 * Method is used to hide keyboard.
 *
 * @param view view
 */
fun Context.hideSoftKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}
