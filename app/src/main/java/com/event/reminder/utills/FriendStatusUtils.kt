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
                    UserActionView.BLOCK_VIEW -> {
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
                if (userActionView == UserActionView.BLOCK_VIEW) {
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
     * @param userActionView
     */
    fun updateStatus(
        view: Button,
        friendStatus: Int,
        actionUserId: String,
        userActionView: Int
    ) {
        LoggerUtils.debug(
            TAG,
            "updateStatus [friendStatus : $friendStatus, userActionView : $userActionView]"
        )
        try {
            val isActionUser = EventReminderSharedPrefUtils.getUserId() == actionUserId
            when (friendStatus) {
                FriendStatus.NOT_A_FRIEND -> {

                    view.text = if (userActionView == UserActionView.BLOCK_VIEW) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.add_friend)
                    }
                }
                FriendStatus.PENDING -> {
                    when (userActionView) {
                        UserActionView.FRIEND_VIEW -> {
                            view.text = if (isActionUser) {
                                view.context.getString(R.string.friend_request_sent)
                            } else {
                                view.context.getString(R.string.accept)
                            }
                        }
                        UserActionView.REJECT_VIEW -> {
                            // TODO handle reject view.
                            view.text = view.context.getString(R.string.reject)
                        }
                        UserActionView.BLOCK_VIEW -> {
                            view.text = view.context.getString(R.string.block)
                        }
                        else -> {
                            view.text = view.context.getString(R.string.unknown)
                        }
                    }
                }
                FriendStatus.ACCEPTED -> {
                    view.text = if (userActionView == UserActionView.BLOCK_VIEW) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.un_friend)
                    }
                }
                FriendStatus.REJECTED -> {
                    view.text = if (userActionView == UserActionView.BLOCK_VIEW) {
                        view.context.getString(R.string.block)
                    } else {
                        view.context.getString(R.string.add_friend)
                    }
                }
                FriendStatus.BLOCKED -> {
                    if (userActionView == UserActionView.BLOCK_VIEW) {
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
                    view.text = if (userActionView == UserActionView.BLOCK_VIEW) {
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