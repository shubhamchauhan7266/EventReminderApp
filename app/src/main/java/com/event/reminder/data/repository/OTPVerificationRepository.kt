package com.event.reminder.data.repository

import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.GetOTPRequest
import com.event.reminder.data.model.request.ValidateOTPRequest

object OTPVerificationRepository : BaseRepository()  {

    fun getOTP(
        request: GetOTPRequest,
        _getOTPResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {
            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getOTP(request),
                object : SubscriptionCallback<BaseResponseModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        _getOTPResult.value = ApiResult(success = response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _getOTPResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })
        } catch (e: Throwable) {
            _getOTPResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    fun validateOTP(
        request: ValidateOTPRequest,
        _validateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {
            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.validateOTP(request),
                object : SubscriptionCallback<BaseResponseModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        _validateOTPResult.value = ApiResult(success = response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _validateOTPResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })
        } catch (e: Throwable) {
            _validateOTPResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}