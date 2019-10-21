package com.event.reminder.ui.dashboard.friends

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.model.request.FriendRequestDetailsListRequest
import com.event.reminder.data.model.response.FriendRequestDetailsModel
import com.event.reminder.data.repository.RequestListRepository

class RequestListViewModel(private val requestListRepository: RequestListRepository) : BaseObservableViewModel() {

    private val _friendRequestDetailsResult: MutableLiveData<ApiResult<FriendRequestDetailsModel>> = MutableLiveData()

    fun getFriendRequestDetailsApiResult(): LiveData<ApiResult<FriendRequestDetailsModel>> {
        // can be launched in a separate asynchronous job
        val request = FriendRequestDetailsListRequest(userId = "")
        requestListRepository.getFriendRequestDetails(request, _friendRequestDetailsResult)
        return _friendRequestDetailsResult
    }
}