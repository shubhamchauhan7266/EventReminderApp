package com.event.reminder.ui.dashboard.friends.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.request.FriendListRequest
import com.event.reminder.data.model.response.FriendDetailsModel
import com.event.reminder.data.repository.FriendListRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class FriendListViewModel(private val friendListRepository: FriendListRepository) : BaseObservableViewModel() {

    private val _friendDetailsResult: MutableLiveData<ApiResult<FriendDetailsModel>> = MutableLiveData()

    /**
     * Method is used to fetch friend details.
     * @return LiveData object to observe friend details
     */
    fun getFriendDetailsApiResult(): LiveData<ApiResult<FriendDetailsModel>> {
        // can be launched in a separate asynchronous job
        val request = FriendListRequest(userId = EventReminderSharedPrefUtils.getUserId())
        friendListRepository.getFriendDetails(request, _friendDetailsResult)
        return _friendDetailsResult
    }
}