package com.android.mvvmandroidlib.data

import java.io.Serializable

open class BaseResponseModel(
    var status: Boolean = false,
    var statusCode: Int = 0,
    var errorMessage: String? = "",
    var response: Any? = null
) : Serializable