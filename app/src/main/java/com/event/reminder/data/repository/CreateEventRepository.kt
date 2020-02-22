package com.event.reminder.data.repository

import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.api.RequestNetworkManager
import com.android.mvvmandroidlib.api.SubscriptionCallback
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.repository.BaseRepository
import com.event.reminder.api.EventReminderApiHandler
import com.event.reminder.data.model.request.CreateEventRequest
import com.event.reminder.data.model.request.UpdateEventRequest

/**
 * This repository class is used to call API related to Event when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object CreateEventRepository : BaseRepository() {

    /**
     * Method is used to create event on database.
     * @param request [CreateEventRequest]
     * @param _createEventApiResult LiveData object
     */
    fun createEvent(
        request: CreateEventRequest,
        _createEventApiResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.createEvent(request)
                ?.let {
                    RequestNetworkManager.addRequest(
                        it,
                        object : SubscriptionCallback<BaseResponseModel> {

                            override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                                _createEventApiResult.value = ApiResult(success = response)
                            }

                            override fun onException(
                                requestCode: Int,
                                errCode: Int,
                                errorMsg: String
                            ) {
                                _createEventApiResult.value =
                                    ApiResult(errorMessage = errorMsg, errorCode = errCode)
                            }

                        })
                }

        } catch (e: Throwable) {
            _createEventApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }

    /**
     * Method is used to update event on database.
     * @param request [UpdateEventRequest]
     * @param _updateEventApiResult LiveData object
     */
    fun updateEvent(
        request: UpdateEventRequest,
        _updateEventApiResult: MutableLiveData<ApiResult<BaseResponseModel>>
    ) {
        try {

            EventReminderApiHandler.getAPIHandler()?.getAPIClient()?.updateEvent(request)
                ?.let {
                    RequestNetworkManager.addRequest(
                        it,
                        object : SubscriptionCallback<BaseResponseModel> {

                            override fun onSuccess(requestCode: Int, response: BaseResponseModel) {

                                _updateEventApiResult.value = ApiResult(success = response)
                            }

                            override fun onException(
                                requestCode: Int,
                                errCode: Int,
                                errorMsg: String
                            ) {
                                _updateEventApiResult.value =
                                    ApiResult(errorMessage = errorMsg, errorCode = errCode)
                            }

                        })
                }

        } catch (e: Throwable) {
            _updateEventApiResult.value = ApiResult(errorMessage = e.localizedMessage)
        }
    }
}