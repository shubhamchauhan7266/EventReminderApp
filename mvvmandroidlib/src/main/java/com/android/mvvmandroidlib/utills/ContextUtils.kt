package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context

/**
 * This util class is used to provide some common methods related to context and object.
 * Ex - isActivityDestroyed, isContextNull etc..
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object ContextUtils {

    /**
     * Method is used to know that activity is destroyed or not.
     *
     * @param activity activity instance
     * @return true if activity is destroyed otherwise else.
     */
    fun isActivityDestroyed(activity: Activity?): Boolean {
        return activity == null || activity.isDestroyed || activity.isFinishing
    }

    /**
     * Method is used to know that context is null or not.
     *
     * @param context context instance
     * @return true if context is null otherwise else.
     */
    fun isContextNull(context: Context?): Boolean {
        return context == null
    }

    /**
     * Method is used to know that any object is null or not.
     *
     * @param anyObject object instance
     * @return true if object is null otherwise else.
     */
    fun isObjectNull(anyObject: Any?): Boolean {
        return anyObject == null
    }
}