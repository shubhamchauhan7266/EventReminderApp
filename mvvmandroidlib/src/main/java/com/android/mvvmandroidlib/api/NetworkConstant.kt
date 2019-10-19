package com.android.mvvmandroidlib.api

/**
 * This constant class is used to provide some network constant.
 *
 * @author Shubham Chauhan
 */
object NetworkConstant {

    const val ERROR_CODE_INVALID: Int = -1
    const val ERROR_CODE_IO_EXCEPTION: Int = 1001
    const val ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION: Int = 1002
    const val ERROR_CODE_NO_INTERNET: Int = 1003
    const val ERROR_CODE_UNKNOWN_HOST_EXCEPTION: Int = 1005
    const val ERROR_CODE_SOCKET_EXCEPTION: Int = 1006

    const val ERROR_CODE_BAD_REQUEST: Int = 400
    const val ERROR_CODE_UNAUTHORISED: Int = 401
    const val ERROR_CODE_FORBIDDEN: Int = 403
    const val ERROR_CODE_URL_NOT_FOUND: Int = 404
    const val ERROR_CODE_GONE: Int = 410
    const val ERROR_CODE_SERVER_DOWN: Int = 500
    const val ERROR_CODE_SERVICE_UNAVAILABLE: Int = 503

    const val ERROR_CODE_EXPIRE_TOKEN: Int = 2000
    const val ERROR_CODE_EXCEPTION: Int = 2001

    /**
     * @return error message with respect to error code.
     */
    fun getErrorMessage(code: Int): String {

        when (code) {
            ERROR_CODE_INVALID -> {
                return "Something Went Wrong"
            }
            ERROR_CODE_IO_EXCEPTION -> {
                return "IO Exception"
            }
            ERROR_CODE_SOCKET_TIMEOUT_EXCEPTION -> {
                return "Socket Timeout Exception"
            }
            ERROR_CODE_NO_INTERNET -> {
                return "No Internet Connection!!"
            }
            ERROR_CODE_SERVER_DOWN -> {
                return "Temporary Server Down"
            }
            ERROR_CODE_UNKNOWN_HOST_EXCEPTION -> {
                return "Unknown Host Exception"
            }
            ERROR_CODE_SOCKET_EXCEPTION -> {
                return "Socket Exception"
            }
            ERROR_CODE_EXPIRE_TOKEN -> {
                return "Expiry Token"
            }
            ERROR_CODE_EXCEPTION -> {
                return "Exception Occurred"
            }
            else -> {
                return "Something Went Wrong"
            }
        }
    }
}