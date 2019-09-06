package com.android.mvvmandroidlib.data

import java.io.Serializable

/**
 * This model class can be used as a base class for all Response Model.
 *
 * @param status status of response
 * @param statusCode response code
 * @param errorMessage error message
 * @param response response
 *
 * @author Shubham Chauhan
 */
open class BaseResponseModel(
    var status: Boolean = false,
    var statusCode: Int = 0,
    var errorMessage: String? = "",
    var response: Any? = null
) : Serializable