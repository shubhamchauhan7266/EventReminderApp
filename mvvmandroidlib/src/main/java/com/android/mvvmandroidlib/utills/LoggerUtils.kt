package com.android.mvvmandroidlib.utills

import android.util.Log
import com.android.mvvmandroidlib.BuildConfig

/**
 * This util class is used to provide methods to print logs.
 * This logs will print only in debug mode.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object LoggerUtils {

    /**
     * Send a verbose log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun verbose(tag: String, msg: String) {

        if (BuildConfig.DEBUG)
            Log.v(tag, msg)
    }

    /**
     * Send a debug log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun debug(tag: String, msg: String) {

        if (BuildConfig.DEBUG)
            Log.d(tag, msg)
    }

    /**
     * Send a info log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun info(tag: String, msg: String) {

        if (BuildConfig.DEBUG)
            Log.i(tag, msg)
    }

    /**
     * Send a warn log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun warn(tag: String, msg: String) {

        if (BuildConfig.DEBUG)
            Log.w(tag, msg)
    }

    /**
     * Send a error log message.
     * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    fun error(tag: String, msg: String) {

        if (BuildConfig.DEBUG)
            Log.e(tag, msg)
    }

    /**
     * Handy function to get a loggable stack trace from a Throwable
     * @param th An exception to log
     *
     * @return loggable stack trace
     */
    fun getStackTraceString(th: Throwable?): String {
        if (th == null) {
            return ""
        }
        return Log.getStackTraceString(th)
    }
}

