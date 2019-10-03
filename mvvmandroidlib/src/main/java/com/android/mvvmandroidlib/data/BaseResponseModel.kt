package com.android.mvvmandroidlib.data

import java.io.Serializable

/**
 * This model class can be used as a base class for all Response Model.
 *
 * @param success status of response
 * @param statusCode response code
 * @param errorMessage error message
 *
 * @author Shubham Chauhan
 */
open class BaseResponseModel(
    var success: Boolean = false,
    var statusCode: Int = 0,
    var errorMessage: String? = ""
) : Serializable