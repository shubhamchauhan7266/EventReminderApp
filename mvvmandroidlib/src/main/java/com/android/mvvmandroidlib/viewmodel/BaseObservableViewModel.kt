package com.android.mvvmandroidlib.viewmodel

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.android.mvvmandroidlib.data.ProgressEvent
import com.android.mvvmandroidlib.helper.EventLiveData

/**
 * This Observable class is used as a base class for all ViewModel to provide ObservableViewModel.
 * This class has method notifyPropertyChanged(fieldId), which is used by all subclasses.
 *
 * @author Shubham Chauhan
 */
open class BaseObservableViewModel : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()
    val progressEvent = EventLiveData<ProgressEvent>()
    val failedEventErrorCode = EventLiveData<Int>()
    val failedEventErrorMessage = EventLiveData<String>()

    /**
     * Add property change callback
     */
    override fun addOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.add(callback)
    }

    /**
     * Remove property change callback.
     */
    override fun removeOnPropertyChangedCallback(
        callback: Observable.OnPropertyChangedCallback
    ) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
}