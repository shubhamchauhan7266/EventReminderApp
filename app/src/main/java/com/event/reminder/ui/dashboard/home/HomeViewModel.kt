package com.event.reminder.ui.dashboard.home

import com.android.mvvmandroidlib.helper.EventLiveData
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.repository.HomeRepository
import com.event.reminder.enums.NavigationScreen

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class HomeViewModel(homeRepository: HomeRepository) : BaseObservableViewModel() {

    val navigationEvent = EventLiveData<NavigationScreen>()

    /**
     * Navigate to profile screen by click on profile icon.
     */
    fun onProfileClick() {
        navigationEvent.sendEvent(NavigationScreen.PROFILE_SCREEN)
    }

    /**
     * Navigate to create event screen by click on create icon.
     */
    fun createEvent() {
        navigationEvent.sendEvent(NavigationScreen.CREATE_EVENT_SCREEN)
    }
}