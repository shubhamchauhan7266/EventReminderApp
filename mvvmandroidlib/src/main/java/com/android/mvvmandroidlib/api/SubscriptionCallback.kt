package com.android.mvvmandroidlib.api

/**
 * Subscription Callback interface to be implemented by callers
 */
interface SubscriptionCallback<T> {
    fun onSuccess(requestCode: Int, response: T)
    fun onError(requestCode: Int, errCode: Int, errorMsg: String)
}