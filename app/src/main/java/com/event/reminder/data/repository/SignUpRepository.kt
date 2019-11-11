package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.SignUpRequest

object SignUpRepository : BaseRepository() {

    fun signUp(
        signUpRequest: SignUpRequest,
        _signUpResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {

        try {
            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.signUp(signUpRequest),
                object : SubscriptionCallback<BaseResponseModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        _signUpResult.value = ApiResult(success = response)
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _signUpResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })
        } catch (e: Throwable) {
            _signUpResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}