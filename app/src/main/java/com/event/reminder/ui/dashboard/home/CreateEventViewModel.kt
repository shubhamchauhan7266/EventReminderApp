package com.event.reminder.ui.dashboard.home

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.mvvmandroidlib.data.BaseResponseModel
import com.android.mvvmandroidlib.helper.ApiResult
import com.android.mvvmandroidlib.utills.DateUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.android.mvvmandroidlib.viewmodel.BaseObservableViewModel
import com.event.reminder.BR
import com.event.reminder.constant.EventType
import com.event.reminder.data.model.request.CreateEventRequest
import com.event.reminder.data.model.request.UpdateEventRequest
import com.event.reminder.data.repository.CreateEventRepository
import com.event.reminder.utills.EventReminderSharedPrefUtils

/**
 * This ViewModel class is worked as a Observable ViewModel that is responsible for preparing and managing
 * the data for an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 *
 * @author Shubham Chauhan
 */
class CreateEventViewModel(private val createEventRepository: CreateEventRepository) :
    BaseObservableViewModel() {

    private val _createEventResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    val createEventResult: LiveData<ApiResult<BaseResponseModel>>? = _createEventResult
    private val _updateEventResult: MutableLiveData<ApiResult<BaseResponseModel>> =
        MutableLiveData()
    val updateEventResult: LiveData<ApiResult<BaseResponseModel>>? = _updateEventResult

    var eventTitle: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.eventTitle)
        }

    var eventDescription: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.eventDescription)
        }

    var eventLocation: String? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.eventLocation)
        }

    var importentEvent: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.importentEvent)
        }

    var alarmRequired: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.alarmRequired)
        }

    var eventDate: Long = DateUtils.getCurrentTimeStamp()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.eventDate)
        }

    var eventType: Int = EventType.SELF_EVENT
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.eventType)
        }

    /**
     * Method is used to call create event API.
     */
    fun createEvent() {
        val request = CreateEventRequest(
            createdBy = EventReminderSharedPrefUtils.getUserId(),
            eventTitle = eventTitle ?: StringUtils.EMPTY,
            eventDescription = eventDescription ?: StringUtils.EMPTY,
            eventType = eventType,
            eventDate = eventDate ?: DateUtils.getCurrentTimeStamp(),
            isImportant = importentEvent,
            isAlarmUsed = alarmRequired,
            isTaskListPresent = false,
            location = eventLocation ?: StringUtils.EMPTY,
            createdFor = null
        )
        createEventRepository.createEvent(request, _createEventResult)
    }

    /**
     * Method is used to call update event API.
     */
    fun updateEvent() {
        val request = UpdateEventRequest(
            eventId = StringUtils.EMPTY,
            createdBy = EventReminderSharedPrefUtils.getUserId(),
            eventTitle = eventTitle ?: StringUtils.EMPTY,
            eventDescription = eventDescription ?: StringUtils.EMPTY,
            eventType = eventType,
            eventDate = eventDate ?: DateUtils.getCurrentTimeStamp(),
            isImportant = importentEvent,
            isAlarmUsed = alarmRequired,
            isTaskListPresent = false,
            location = eventLocation ?: StringUtils.EMPTY,
            createdFor = null
        )
        createEventRepository.updateEvent(request, _createEventResult)
    }
}