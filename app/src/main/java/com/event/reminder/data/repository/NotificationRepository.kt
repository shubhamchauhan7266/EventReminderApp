package com.event.reminder.data.repository

import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.response.NotificationDetailsModel

object NotificationRepository : BaseRepository() {

    fun getNotificationDetails(
        userId: String,
        _notificationDetailsApiResult: MutableLiveData<ApiResult<NotificationDetailsModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getNotificationDetailsList(userId),
                object : SubscriptionCallback<NotificationDetailsModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is NotificationDetailsModel) {
                            _notificationDetailsApiResult.value = ApiResult(success = response)
                        } else {
                            _notificationDetailsApiResult.value =
                                ApiResult(
                                    errorMessage = response.errorMessage,
                                    errorCode = response.statusCode
                                )
                        }
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _notificationDetailsApiResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })

        } catch (e: Throwable) {
            _notificationDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}