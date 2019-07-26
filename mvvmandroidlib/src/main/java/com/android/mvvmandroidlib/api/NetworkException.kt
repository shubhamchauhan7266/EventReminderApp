package com.android.mvvmandroidlib.api

import com.android.mvvmandroidlib.api.NetworkConstant.ERROR_CODE_INVALID
import okhttp3.Headers

data class NetworkException(
    var exceptionCode: Int = ERROR_CODE_INVALID,
    var exceptionMessage: String = "",
    var header: Headers? = null
) : Throwable()