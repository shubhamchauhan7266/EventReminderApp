package com.event.reminder.utills

import android.view.View
import android.widget.Button
import com.android.mvvmandroidlib.utills.LoggerUtils
import com.android.mvvmandroidlib.utills.StringUtils
import com.event.reminder.R
import com.event.reminder.constant.FriendStatus
import com.event.reminder.constant.UserActionView

/**
 * This utils class is used to provide some common methods which is accessed by whole app related to friend status.
 *
 * @author Shubham Chauhan
 */
object FriendStatusUtils {

    private val TAG = FriendStatusUtils::class.java.simpleName

    /**
     * Method is used to get updated friend status on the basis of user action.
     *
     * @param currentFriendStatus
     * @param userActionView
     * @return updated friend status.
     */
    fun getUpdatedFriendStatus(currentFriendStatus: Int, userActionView: Int): Int {
        LoggerUtils.debug(
            TAG,
            "getUpdatedFriendStatus [currentFriendStatus : $currentFriendStatus, userActionView : $userActionView]"
        )
        return when (currentFriendStatus) {
            FriendStatus.NOT_A_FRIEND -> {
                if (userActionView == UserActionView.FRIEND_VIEW) {
                    FriendStatus.PENDING
                } else {
                    FriendStatus.BLOCKED
                }
            }
            FriendStatus.PENDING -> {
                when (userActionView) {
                    UserActionView.FRIEND_VIEW -> {
                        FriendStatus.ACCEPTED
                    }
                    UserActionView.REJECT_VIEW -> {
                        FriendStatus.REJECTED
                    }
                    UserActionView.BLOCKE_VIEW -> {
                        FriendStatus.BLOCKED
                    }
                    else -> {
                        FriendStatus.NOT_A_FRIEND
                    }
                }
            }
            FriendStatus.ACCEPTED -> {
                if (userActionView == UserActionView.FRIEND_VIEW) {
                    FriendStatus.UN_FRIEND
                } else {
                    FriendStatus.BLOCKED
                }
            }
            FriendStatus.REJECTED -> {
                if (userActionView == UserActionView.FRIEND_VIEW) {
                    FriendStatus.PENDING
                } else {
                    FriendStatus.BLOCKED
                }
            }
            FriendStatus.BLOCKED -> {
                if (userActionView == UserActionView.BLOCKE_VIEW) {
                    FriendStatus.UN_BLOCKED
                } else {
                    FriendStatus.NOT_A_FRIEND
                }
            }
            FriendStatus.UN_FRIEND -> {
                if (userActionView == UserActionView.FRIEND_VIEW) {
                    FriendStatus.PENDING
                } else {
                    FriendStatus.BLOCKED
                }
            }
            FriendStatus.UN_BLOCKED -> {
                if (userActionView == UserActionView.FRIEND_VIEW) {
                    FriendStatus.PENDING
                } else {
                    FriendStatus.BLOCKED
                }
            }
            else -> {
                FriendStatus.NOT_A_FRIEND
            }
        }
    }

    /**
     * Method is used to update UI according to friend status.
     *
     * @param view
     * @param friendStatus
     * @param actionUserId
     * @param isBlockStatusView
     */
    fun updateStatus(
        view: Button,
        friendStatus: Int,
        actionUserId: String,
        isBlockStatusView: Boolean
    ) {
        LoggerUtils.debug(
            TAG,
            "updateStatus [friendStatus : $friendStatus, isBlockStatusView : $isBlockStatusView]"
        )
        try {
            val isActionUser = EventReminderSharedPrefUtils.getUserId() == actionUserId
            when (friendStatus) {
                FriendStatus.NOT_A_FRIEND -> {

                    view.text = if (isBlockStatusView) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.add_friend)
                    }
                }
                FriendStatus.PENDING -> {

                    view.text = if (isBlockStatusView) {
                        view.context.getString(R.string.block)
                    } else {
                        if (isActionUser) {
                            view.context.getString(R.string.friend_request_sent)
                        } else {
                            view.context.getString(R.string.accept)
                        }
                    }
                }
                FriendStatus.ACCEPTED -> {
                    view.text = if (isBlockStatusView) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.un_friend)
                    }
                }
                FriendStatus.REJECTED -> {
                    view.text = if (isBlockStatusView) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.add_friend)
                    }
                }
                FriendStatus.BLOCKED -> {
                    if (isBlockStatusView) {
                        if (isActionUser) {
                            view.text = view.context.getString(R.string.un_block)
                        } else {
                            view.text = view.context.getString(R.string.blocked)
                        }
                    } else {
                        view.visibility = View.GONE
                    }
                }
                FriendStatus.UN_BLOCKED -> {
                    view.text = if (isBlockStatusView) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.add_friend)
                    }
                }
            }
        } catch (e: Exception) {
            LoggerUtils.error(
                TAG,
                "updateStatus : " + LoggerUtils.getStackTraceString(e)
            )
            view.text = StringUtils.EMPTY
        }
    }

}