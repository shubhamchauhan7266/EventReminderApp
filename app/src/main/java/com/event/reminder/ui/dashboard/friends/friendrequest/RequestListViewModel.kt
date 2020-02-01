package com.event.reminder.ui.dashboard.friends.friendrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.request.FriendRequestDetailsListRequest
import com.event.reminder.data.model.response.FriendRequestDetailsModel
import com.event.reminder.data.repository.RequestListRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class RequestListViewModel(private val requestListRepository: RequestListRepository) : BaseObservableViewModel() {

    private val _friendRequestDetailsResult: MutableLiveData<ApiResult<FriendRequestDetailsModel>> = MutableLiveData()

    fun getFriendRequestDetailsApiResult(requestType: Int): LiveData<ApiResult<FriendRequestDetailsModel>> {
        // can be launched in a separate asynchronous job
        val request = FriendRequestDetailsListRequest(
            userId = EventReminderSharedPrefUtils.getUserId(),
            requestType = requestType
        )
        requestListRepository.getFriendRequestDetails(request, _friendRequestDetailsResult)
        return _friendRequestDetailsResult
    }
}