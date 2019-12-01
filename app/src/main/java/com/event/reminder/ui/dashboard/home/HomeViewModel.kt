package com.event.reminder.ui.dashboard.home

import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.repository.HomeRepository
import com.event.reminder.enums.NavigationScreen

class HomeViewModel(homeRepository: HomeRepository) : BaseObservableViewModel() {

    val navigationEvent = EventLiveData<NavigationScreen>()

    fun onProfileClick() {
        navigationEvent.sendEvent(NavigationScreen.PROFILE_SCREEN)
    }
}