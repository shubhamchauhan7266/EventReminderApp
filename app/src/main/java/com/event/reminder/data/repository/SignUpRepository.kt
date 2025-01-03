package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.SignUpRequest

/**
 * This repository class is used to call API related to Sign up when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object SignUpRepository : BaseRepository() {

    /**
     * Method is used to register user by creating account.
     * @param signUpRequest SignUpRequest
     * @param _signUpResult LiveData object
     */
    fun signUp(
        signUpRequest: SignUpRequest,
        _signUpResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {

        try {
            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.signUp(signUpRequest)?.let {
                RequestNetworkManager.addRequest(
                    it,
                    object : SubscriptionCallback<BaseResponseModel> {

                        override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                            _signUpResult.value = ApiResult(success = response)
                        }

                        override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                            _signUpResult.value =
                                ApiResult(errorMessage = errorMsg, errorCode = errCode)
                        }

                    })
            }
        } catch (e: Throwable) {
            _signUpResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}