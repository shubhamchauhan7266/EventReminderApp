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

/**
 * This repository class is used to call API related to Friend Request List when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object RequestListRepository : BaseRepository() {

    /**
     * Method is used to fetch friend request details from database.
     * @param request FriendRequestDetailsListRequest
     * @param _friendRequestDetailsApiResult LiveData object
     */
    fun getFriendRequestDetails(
        request: FriendRequestDetailsListRequest,
        _friendRequestDetailsApiResult: MutableLiveData<ApiResult<FriendRequestDetailsModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.getFriendRequestDetailsList(
                request
            )?.let {
                RequestNetworkManager.addRequest(
                    it,
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
            }

        } catch (e: Throwable) {
            _friendRequestDetailsApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}