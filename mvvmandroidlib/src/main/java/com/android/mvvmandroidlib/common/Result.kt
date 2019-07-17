package com.android.mvvmandroidlib.common

import com.android.mvvmandroidlib.data.BaseModel


/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : BaseModel> {

    data class Success<out T : BaseModel>(val data: T) : Result<T>()
    data class Error(val error: String) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[error=$error]"
        }
    }
}
