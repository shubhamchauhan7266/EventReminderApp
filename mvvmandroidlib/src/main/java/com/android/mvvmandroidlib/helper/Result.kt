package com.android.mvvmandroidlib.helper


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
data class ApiResult<T>(
    val success: T? = null,
    val errorCode: Int = 101,
    val errorMessage: String? = null
)
