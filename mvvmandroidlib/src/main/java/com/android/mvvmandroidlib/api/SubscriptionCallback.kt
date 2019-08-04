package com.android.mvvmandroidlib.api

import com.android.mvvmandroidlib.data.BaseResponseModel

/**
 * Subscription Callback interface to be implemented by callers
 */
interface SubscriptionCallback<out T : BaseResponseModel> {
    fun onSuccess(requestCode: Int, response: BaseResponseModel)
    fun onException(requestCode: Int, errCode: Int, errorMsg: String)
}