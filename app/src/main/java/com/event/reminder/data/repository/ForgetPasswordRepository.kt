package com.event.reminder.data.repository

import com.android.mvvmandroidlib.repository.BaseRepository

/**
 * This repository class is used to call API related to Forget Password when network is available
 * to maintains an in-memory cache and fetch updated data from that.
 * When network is not available then it only get data from local database.
 *
 * @author Shubham Chauhan
 */
object ForgetPasswordRepository : BaseRepository() {
}