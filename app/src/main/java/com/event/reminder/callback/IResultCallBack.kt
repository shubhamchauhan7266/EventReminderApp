package com.event.reminder.callback

/**
 * This callback is used for result which can be either success or failure.
 *
 * @author Shubham Chauhan
 */
interface IResultCallBack<T> {
    fun onSuccess(result: T)
    fun onFailure(error: String)
}