package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.FriendRequestDetailsListRequest
import com.event.reminder.data.model.response.FriendRequestDetailsModel

object RequestListRepository : BaseRepository() {

    fun getFriendRequestDetails(
        request: FriendRequestDetailsListRequest,
        _friendRequestDetailsApiResult: MutableLiveData<ApiResult<FriendRequestDetailsModel>>
    ) {
        try {

            RequestNetworkManager.addRequest(
                EventReminderApiHandler.getAPIHandler()?.getAPIClient()!!.getFriendRequestDetailsList(
                    request
                ),
                object : SubscriptionCallback<FriendRequestDetailsModel> {

                    override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                        if (response is FriendRequestDetailsModel) {
                            response.friendRequestDetailsList.forEach {
                                it.requestType = request.requestType
                            }
                            _friendRequestDetailsApiResult.value = ApiResult(success = response)
                        } else {
                            _friendRequestDetailsApiResult.value =
                                ApiResult(
                                    errorMessage = response.errorMessage,
                                    errorCode = response.statusCode
                                )
                        }
                    }

                    override fun onException(requestCode: Int, errCode: Int, errorMsg: String) {
                        _friendRequestDetailsApiResult.value =
                            ApiResult(errorMessage = errorMsg, errorCode = errCode)
                    }

                })

        } catch (e: Throwable) {
            _friendRequestDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}