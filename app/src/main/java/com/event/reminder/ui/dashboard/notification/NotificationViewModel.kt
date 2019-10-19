package com.event.reminder.ui.dashboard.notification

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.request.NotificationDetailsListRequest
import com.event.reminder.data.model.response.NotificationDetailsModel
import com.event.reminder.data.repository.NotificationRepository

class NotificationViewModel(private val notificationRepository: NotificationRepository) : BaseObservableViewModel() {

    private val _notificationDetailsResult: MutableLiveData<ApiResult<NotificationDetailsModel>> = MutableLiveData()

    fun getNotificationDetailsApiResult(): LiveData<ApiResult<NotificationDetailsModel>> {
        // can be launched in a separate asynchronous job
        val request = NotificationDetailsListRequest(accessToken = null, userId = "")
        notificationRepository.getNotificationDetails(request, _notificationDetailsResult)
        return _notificationDetailsResult
    }
}