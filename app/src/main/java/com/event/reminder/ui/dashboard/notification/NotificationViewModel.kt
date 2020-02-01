package com.event.reminder.ui.dashboard.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.response.NotificationDetailsModel
import com.event.reminder.data.repository.NotificationRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class NotificationViewModel(private val notificationRepository: NotificationRepository) : BaseObservableViewModel() {

    private val _notificationDetailsResult: MutableLiveData<ApiResult<NotificationDetailsModel>> = MutableLiveData()

    fun getNotificationDetailsApiResult(): LiveData<ApiResult<NotificationDetailsModel>> {
        // can be launched in a separate asynchronous job
        notificationRepository.getNotificationDetails(EventReminderSharedPrefUtils.getUserId(), _notificationDetailsResult)
        return _notificationDetailsResult
    }
}