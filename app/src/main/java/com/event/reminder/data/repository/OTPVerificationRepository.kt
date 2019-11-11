package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.GenerateOTPRequest
import com.event.reminder.data.model.request.ValidateOTPRequest

object OTPVerificationRepository : BaseRepository()  {

    fun generateOTP(
        request: GenerateOTPRequest,
        _generateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {
            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.generateOTP(request),
                object : SubscriptionCallback<BaseResponseModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        _generateOTPResult.value = ApiResult(success = response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _generateOTPResult.value =
                            ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })
        } catch (e: Throwable) {
            _generateOTPResult.value = ApiResult(errorMessage = e.localizedMessage)
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