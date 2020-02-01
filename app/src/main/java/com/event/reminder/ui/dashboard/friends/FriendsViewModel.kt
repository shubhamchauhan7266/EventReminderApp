package com.event.reminder.ui.dashboard.friends

import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.data.repository.FriendsRepository

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class FriendsViewModel(friendsRepository: FriendsRepository) : BaseObservableViewModel() {
}