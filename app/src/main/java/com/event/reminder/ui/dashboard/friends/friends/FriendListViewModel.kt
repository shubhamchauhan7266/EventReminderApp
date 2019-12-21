package com.event.reminder.ui.dashboard.friends.friends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.request.FriendDetailsListRequest
import com.event.reminder.data.model.response.FriendDetailsModel
import com.event.reminder.data.repository.FriendListRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

class FriendListViewModel(private val friendListRepository: FriendListRepository) : BaseObservableViewModel() {

    private val _friendDetailsResult: MutableLiveData<ApiResult<FriendDetailsModel>> = MutableLiveData()

    fun getFriendDetailsApiResult(): LiveData<ApiResult<FriendDetailsModel>> {
        // can be launched in a separate asynchronous job
        val request = FriendDetailsListRequest(userId = EventReminderSharedPrefUtils.getUserId())
        friendListRepository.getFriendDetails(request, _friendDetailsResult)
        return _friendDetailsResult
    }
}