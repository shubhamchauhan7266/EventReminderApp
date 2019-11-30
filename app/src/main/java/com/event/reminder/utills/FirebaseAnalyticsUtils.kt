package com.event.reminder.utills

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * This utils class is used to log event in fire base server.
 *
 * @author Shubham Chauhan
 */
object FirebaseAnalyticsUtils {

    fun logEvent(
        context: AppCompatActivity,
        itemID: String,
        itemName: String,
        contentType: String
    ) {
        val fireBaseAnalytics = FirebaseAnalytics.getInstance(context)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemID)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, itemName)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType)
        fireBaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}