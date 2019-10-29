package com.event.reminder.ui.dashboard.home

import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.constant.NavigationConstant
import com.event.reminder.data.repository.HomeRepository

class HomeViewModel(homeRepository: HomeRepository) : BaseObservableViewModel() {

    val navigationEvent = EventLiveData<NavigationConstant>()

    fun onProfileClick() {
        navigationEvent.sendEvent(NavigationConstant.PROFILE_SCREEN)
    }
}