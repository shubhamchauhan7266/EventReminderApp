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

/**
 * This repository class is used to call API related to OTP when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object OTPVerificationRepository : BaseRepository()  {

    /**
     * Method is used to generate OTP through SMS Retriever API.
     * @param request GenerateOTPRequest
     * @param _generateOTPResult LiveData object
     */
    fun generateOTP(
        request: GenerateOTPRequest,
        _generateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {
            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.generateOTP(request)?.let {
                RequestNetworkManager.addRequest(
                    it,
                    object : SubscriptionCallback<BaseResponseModel> {

                        override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                            _generateOTPResult.value = ApiResult(success = response)
                        }

                        override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                            _generateOTPResult.value =
                                ApiResult(errorMessage = errorMsg, errorCode = errCode)
                        }

                    })
            }
        } catch (e: Throwable) {
            _generateOTPResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    /**
     * Method is used to validate OTP through SMS Retriever API.
     * @param request ValidateOTPRequest
     * @param _validateOTPResult LiveData object
     */
    fun validateOTP(
        request: ValidateOTPRequest,
        _validateOTPResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {
            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.validateOTP(request)?.let {
                RequestNetworkManager.addRequest(
                    it,
                    object : SubscriptionCallback<BaseResponseModel> {

                        override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                            _validateOTPResult.value = ApiResult(success = response)
                        }

                        override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                            _validateOTPResult.value =
                                ApiResult(errorMessage = errorMsg, errorCode = errCode)
                        }

                    })
            }
        } catch (e: Throwable) {
            _validateOTPResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}