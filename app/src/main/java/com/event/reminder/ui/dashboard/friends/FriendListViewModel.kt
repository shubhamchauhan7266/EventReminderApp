package com.event.reminder.ui.dashboard.friends

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.request.FriendDetailsListRequest
import com.event.reminder.data.model.response.FriendDetailsModel
import com.event.reminder.data.repository.FriendListRepository

class FriendListViewModel(private val friendListRepository: FriendListRepository) : BaseObservableViewModel() {

    private val _friendDetailsResult: MutableLiveData<ApiResult<FriendDetailsModel>> = MutableLiveData()

    fun getFriendDetailsApiResult(): LiveData<ApiResult<FriendDetailsModel>> {
        // can be launched in a separate asynchronous job
        val request = FriendDetailsListRequest(accessToken = null, userId = "")
        friendListRepository.getFriendDetails(request, _friendDetailsResult)
        return _friendDetailsResult
    }
}