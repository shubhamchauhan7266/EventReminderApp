package com.android.mvvmandroidlib.utills

import androidx.annotation.NonNull
import com.android.mvvmandroidlib.exception.MathUtilParseException

/**
 * This util class is used to provide some methods related to Math or Calculation.
 * Ex - ti find min, max, sum, divide etc...
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object MathUtils {

    private val TAG: String = MathUtils::class.java.simpleName

    /**
     * Method is used to get minimum value from given two values.
     *
     * @param firstValue
     * @param secondValue
     * @return minimum value
     */
    @Throws(MathUtilParseException::class)
    @NonNull
    fun getMin(firstValue: String, secondValue: String): String {
        LoggerUtils.debug(TAG, "getMin($firstValue , $secondValue)")
        try {
            if (StringUtils.isNullOrEmpty(firstValue) || StringUtils.isNullOrEmpty(secondValue)) {
                throw MathUtilParseException("Value can not be empty or null")
            }
            return getMin(firstValue.toInt(), secondValue.toInt()).toString()
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw MathUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get minimum value from given two values.
     *
     * @param firstValue
     * @param secondValue
     * @return minimum value
     */
    fun getMin(firstValue: Int, secondValue: Int): Int {
        return if (firstValue <= secondValue) {
            firstValue
        } else {
            secondValue
        }
    }

    /**
     * Method is used to get maximum value from given two values.
     *
     * @param firstValue
     * @param secondValue
     * @return maximum value
     */
    @Throws(MathUtilParseException::class)
    @NonNull
    fun getMax(firstValue: String, secondValue: String): String {
        LoggerUtils.debug(TAG, "getMax($firstValue , $secondValue)")
        try {
            if (StringUtils.isNullOrEmpty(firstValue) || StringUtils.isNullOrEmpty(secondValue)) {
                throw MathUtilParseException("Value can not be empty or null")
            }
            return getMax(firstValue.toInt(), secondValue.toInt()).toString()
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw MathUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get maximum value from given two values.
     *
     * @param firstValue
     * @param secondValue
     * @return maximum value
     */
    fun getMax(firstValue: Int, secondValue: Int): Int {
        return if (firstValue >= secondValue) {
            firstValue
        } else {
            secondValue
        }
    }
}