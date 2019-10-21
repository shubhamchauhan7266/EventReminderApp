package com.android.mvvmandroidlib.helper

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread

/**
 * This class is used to provide event which is observable.
 * Any event is trigger by this class will give a callback to whom which have observer to listen.
 *
 * @param <T> The type of data hold by this instance
 *
 * @author Shubham Chauhan
 */
class EventLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T?>) {
        // Being strict about the observer numbers is up to you
        // I thought it made sense to only allow one to handle the event
        /*if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }*/

        super.observe(owner, Observer { data ->
            // We ignore any null values and early return
            if (data != null) {
                observer.onChanged(data)
                // We set the value to null straight after emitting the change to the observer
                value = null
            }
        })
    }

    /**
     * Send event when work on Main Thread or UI Thread.
     */
    @MainThread
    fun sendEvent(data: T) {
        value = data
    }

    /**
     * Send event when work on worker thread or in background.
     */
    fun postEvent(data: T) {
        super.postValue(data)
    }
}