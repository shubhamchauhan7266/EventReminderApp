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
 * [ViewModel] is a class that is responsible for preparing and managing the data for
 * an [android.app.Activity] or a [androidx.fragment.app.Fragment].
 * It also handles the communication of the Activity / Fragment with the rest of the application
 * (e.g. calling the business logic classes).
 * <p>
 * A ViewModel is always created in association with a scope (an fragment or an activity) and will
 * be retained as long as the scope is alive. E.g. if it is an Activity, until it is
 * finished.
 * <p>
 * In other words, this means that a ViewModel will not be destroyed if its owner is destroyed for a
 * configuration change (e.g. rotation). The new instance of the owner will just re-connected to the
 * existing ViewModel.
 * <p>
 * The purpose of the ViewModel is to acquire and keep the information that is necessary for an
 * Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the
 * ViewModel. ViewModels usually expose this information via {@link LiveData} or Android Data
 * Binding. You can also use any observability construct from you favorite framework.
 * <p>
 * [Observable] classes provide a way in which data bound UI can be notified of changes.
 * {@link ObservableList} and {@link ObservableMap} also provide the ability to notify when
 * changes occur. ObservableField, ObservableParcelable, ObservableBoolean, ObservableByte,
 * ObservableShort, ObservableInt, ObservableLong, ObservableFloat, and ObservableDouble provide
 * a means by which properties may be notified without implementing Observable.
 * <p>
 * An Observable object should notify the [Observable.OnPropertyChangedCallback] whenever
 * an observed property of the class changes.
 * <p>
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