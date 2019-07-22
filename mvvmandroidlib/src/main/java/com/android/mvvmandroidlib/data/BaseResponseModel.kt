package com.android.mvvmandroidlib.data

import java.io.Serializable

open class BaseResponseModel(
    var status: Boolean = false,
    var responseCode: Int = 0,
    var responseMessage: String = ""
) : Serializable