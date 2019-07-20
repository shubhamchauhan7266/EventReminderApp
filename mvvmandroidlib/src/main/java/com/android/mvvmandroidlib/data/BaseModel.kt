package com.android.mvvmandroidlib.data

import java.io.Serializable

open class BaseModel(
    var status: Boolean = false,
    var responseCode: Int = 0,
    var responseMessage: String = ""
) : Serializable