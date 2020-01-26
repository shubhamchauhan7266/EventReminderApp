package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.FriendListRequest
import com.event.reminder.data.model.response.FriendDetailsModel

/**
 * This repository class is used to call API related to Friend List when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object FriendListRepository : BaseRepository() {

    fun getFriendDetails(
        request: FriendListRequest,
        _friendDetailsApiResult: MutableLiveData<ApiResult<FriendDetailsModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getFriendDetailsList(request),
                object : SubscriptionCallback<FriendDetailsModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is FriendDetailsModel) {
                            _friendDetailsApiResult.value = ApiResult(success = response)
                        } else {
                            _friendDetailsApiResult.value =
                                ApiResult(
                                    errorMessage = response.errorMessage,
                                    errorCode = response.statusCode
                                )
                        }
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _friendDetailsApiResult.value = ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })

        } catch (e: Throwable) {
            _friendDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}