package com.android.mvvmandroidlib.helper


/**
 * A generic class that holds a value of Api result.
 *
 * @param <T> The type of data hold by this instance
 *
 * @author Shubham Chauhan
 */
data class ApiResult<T>(
    val success: T? = null,
    val errorCode: Int = 101,
    val errorMessage: String? = null
)
