package com.event.reminder.service

import android.app.IntentService
import android.content.Intent

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * this service handle friend request action like accept, reject etc...
 *
 * @author Shubham Chauhan
 */
class HandleFriendRequestService : IntentService("HandleFriendRequestService") {

    override fun onHandleIntent(intent: Intent?) {

    }
}
