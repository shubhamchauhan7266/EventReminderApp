package com.android.mvvmandroidlib.api

import com.android.mvvmandroidlib.data.BaseErrorModel
import com.android.mvvmandroidlib.data.BaseResponseModel

/**
 * Subscription Callback interface to be implemented by callers
 */
interface SubscriptionCallback<out T : BaseResponseModel, out V : BaseErrorModel> {
    fun onSuccess(requestCode: Int, response: BaseResponseModel)
    fun onException(requestCode: Int, errCode: Int, errorMsg: String)
    fun onServerError(requestCode: Int, response: BaseErrorModel)
}