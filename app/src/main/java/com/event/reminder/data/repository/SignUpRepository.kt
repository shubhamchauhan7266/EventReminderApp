package com.event.reminder.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.common.ApiResult
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.constant.ErrorConstant
import com.event.reminder.data.model.request.SignUpRequest

object SignUpRepository : BaseRepository() {

    fun signUp(signUpRequest: SignUpRequest): LiveData<ApiResult<BaseResponseModel>>? {

        val result: MutableLiveData<ApiResult<BaseResponseModel>>? = MutableLiveData()
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.signUp(signUpRequest),
                object : SubscriptionCallback<BaseResponseModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        result?.value = ApiResult(success = response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        result?.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })
        } catch (e: Throwable) {
            result?.value = ApiResult(errorCode = ErrorConstant.SERVER_ERROR_FROM_API)
        }

        return result
    }
}