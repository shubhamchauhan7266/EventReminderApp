package com.android.mvvmandroidlib.callback

/**
 * This callback is used for result which can be either success or failure.
 *
 * @param <T> Type of result
 * @author Shubham Chauhan
 */
interface IResultCallBack<T> {
    fun onSuccess(result: T)
    fun onFailure(error: String)
}