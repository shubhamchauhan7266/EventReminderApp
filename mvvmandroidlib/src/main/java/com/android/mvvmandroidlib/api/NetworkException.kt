package com.android.mvvmandroidlib.api

import com.android.mvvmandroidlib.api.NetworkConstant.ERROR_CODE_INVALID
import okhttp3.Headers

/**
 * This exception class is used for Network Exception.
 *
 * @param exceptionCode exception Code
 * @param exceptionMessage exception message
 * @param header header value
 *
 * @author Shubham Chauhan
 */
data class NetworkException(
    var exceptionCode: Int = ERROR_CODE_INVALID,
    var exceptionMessage: String = "",
    var header: Headers? = null
) : Throwable()