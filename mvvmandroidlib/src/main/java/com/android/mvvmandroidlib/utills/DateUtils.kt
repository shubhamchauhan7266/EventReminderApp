package com.android.mvvmandroidlib.utills

import android.support.annotation.NonNull
import com.android.mvvmandroidlib.exception.DateUtilParseException
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

/**
 * This util class is used to provide some common methods related to Date.
 * It provide some helper method to parse and format date. It will throw DateUtilParseException if any exception occurred.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object DateUtils {

    private val TAG: String = DateUtils::class.java.simpleName
    const val DD_MMM_YYYY = "dd MMM yyyy"
    const val HH_MM_AA = "hh:mm aa"
    const val DD_MMM_YYYY_HH_MM_AA = "dd MMM yyyy hh:mm aa"

    /**
     * Method is used to parse date from date in specific format.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param date    date
     * @param inputFormat    inputFormat
     * @return date instance.
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun parseDate(date: String?, inputFormat: String?): Date {
        LoggerUtils.debug(TAG, "parseDate($date , $inputFormat)")
        return try {
            SimpleDateFormat(inputFormat, Locale.US).parse(date)
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to parse date from timestamp.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param timeStamp    timeStamp
     * @return date instance.
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun parseDate(timeStamp: Long?): Date {
        LoggerUtils.debug(TAG, "parseDate($timeStamp)")
        return try {
            if (timeStamp != null && timeStamp != 0L) {
                Date(timeStamp)
            } else {
                throw DateUtilParseException("Timestamp can't be null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to parse date from calendar.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param cal    calendar
     * @return date instance.
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun parseDate(cal: Calendar?): Date {
        LoggerUtils.debug(TAG, "parseDate(Calender)")
        return try {
            if (cal != null) {
                cal.time
            } else {
                throw DateUtilParseException("Calendar instance can't be null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get formatted date from date.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param date    date
     * @param outputFormat outputFormat
     * @return date in String with required format.
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun formatDate(date: Date?, outputFormat: String?): String {
        LoggerUtils.debug(TAG, "formatDate(Date, $outputFormat)")
        return try {
            val sdf = SimpleDateFormat(outputFormat, Locale.getDefault())
            if (date != null) {
                sdf.format(date)
            } else {
                throw DateUtilParseException("Date can't be null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get formatted date from timeStamp.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param timeStamp    timeStamp
     * @param outputFormat outputFormat
     * @return date in String with required format.
     */
//    @Throws(DateUtilParseException::class)
    @NonNull
    fun formatDate(timeStamp: Long?, outputFormat: String?): String {
        LoggerUtils.debug(TAG, "formatDate($timeStamp, $outputFormat)")
        return try {
            formatDate(parseDate(timeStamp), outputFormat)
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get formatted date from calender.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param cal    calender
     * @param outputFormat outputFormat
     * @return date in String with required format.
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun formatDate(cal: Calendar?, outputFormat: String?): String {
        LoggerUtils.debug(TAG, "formatDate(Calender, $outputFormat)")
        return try {
            formatDate(parseDate(cal), outputFormat)
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to convert date from one format to another.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param inputDate    inputDate
     * @param inputFormat  inputFormat
     * @param outputFormat outputFormat
     * @return date in String with required format.
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun formatDate(
        inputDate: String,
        inputFormat: String,
        outputFormat: String
    ): String {
        LoggerUtils.debug(
            TAG,
            "convertDateOneFormatToOther($inputDate, $inputFormat, $outputFormat)"
        )
        return try {
            val date = parseDate(inputDate, inputFormat)
            formatDate(date, outputFormat)
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * @return current timestamp
     */
    @NonNull
    fun getCurrentTimeStamp(): Long {
        LoggerUtils.debug(TAG, "getCurrentTimeStamp()")
        return System.currentTimeMillis()
    }

    /**
     * Method is used to get timestamp for given date.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param date date
     * @return timestamp
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun getTimeStamp(date: Date?): Long {
        LoggerUtils.debug(TAG, "getTimeStamp(Date)")
        return try {
            date?.time ?: throw DateUtilParseException("Date can't be null")
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get timestamp for given calendar.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param cal calendar
     * @return timestamp
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun getTimeStamp(cal: Calendar?): Long {
        LoggerUtils.debug(TAG, "getTimeStamp(Calendar)")
        return try {
            getTimeStamp(
                cal?.time ?: throw DateUtilParseException("Calendar instance can't be null")
            )
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get timestamp for given date in specific format.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param date date
     * @param inputFormat inputFormat
     * @return timestamp
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun getTimeStamp(date: String?, inputFormat: String?): Long {
        LoggerUtils.debug(TAG, "getTimeStamp($date, $inputFormat)")
        return try {
            getTimeStamp(parseDate(date, inputFormat))
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get calendar instance for given timestamp.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param timeStamp timeStamp
     * @return calendar instance
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun getCalendar(timeStamp: Long?): Calendar {
        LoggerUtils.debug(TAG, "getCalendar($timeStamp)")
        val cal = Calendar.getInstance(Locale.US)
        return try {
            if (timeStamp != null && timeStamp != 0L) {
                cal.timeInMillis = timeStamp
                cal
            } else {
                throw DateUtilParseException("Timestamp can't be null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get calendar instance for given date.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param date date
     * @return calendar instance
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun getCalendar(date: Date?): Calendar {
        LoggerUtils.debug(TAG, "getCalendar(Date)")
        val cal = Calendar.getInstance(Locale.US)
        return try {
            if (date != null) {
                cal.time = date
                cal
            } else {
                throw DateUtilParseException("Date can't be null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get calendar instance for given date in specific format.
     * If any exception occurred then it will throw {@link #DateUtilParseException}.
     *
     * @param date date
     * @param inputFormat inputFormat
     * @return calendar instance
     */
    @Throws(DateUtilParseException::class)
    @NonNull
    fun getCalendar(date: String?, inputFormat: String?): Calendar {
        LoggerUtils.debug(TAG, "getCalendar($date, $inputFormat)")
        val cal = Calendar.getInstance(Locale.US)
        return try {
            if (date != null) {
                cal.time = parseDate(date, inputFormat)
                cal
            } else {
                throw DateUtilParseException("Date can't be null")
            }
        } catch (e: Exception) {
            LoggerUtils.error(TAG, LoggerUtils.getStackTraceString(e))
            throw DateUtilParseException(e.localizedMessage)
        }
    }

    /**
     * Method is used to get current time in required format.
     *
     * @param outputFormat outputFormat
     */
    fun getCurrentTime(outputFormat: String?): String {
        LoggerUtils.debug(TAG, "getCurrentTime($outputFormat)")
        return formatDate(getCurrentTimeStamp(), outputFormat)
    }

    /**
     * Method is used to provide current month.
     */
    fun getCurrentMonth(): Int {
        LoggerUtils.debug(TAG, "getCurrentMonth()")
        return Calendar.getInstance(Locale.US).get(Calendar.MONTH)
    }

    /**
     * Method is used to provide current month name.
     */
    fun getCurrentMonthName(): String {
        LoggerUtils.debug(TAG, "getCurrentMonthName()")
        val dfs = DateFormatSymbols(Locale.US)
        return dfs.months[getCurrentMonth()]
    }

    /**
     * Method is used to provide current month short name.
     */
    fun getCurrentMonthShortName(): String {
        LoggerUtils.debug(TAG, "getCurrentMonthShortName()")
        val dfs = DateFormatSymbols(Locale.US)
        return dfs.shortMonths[getCurrentMonth()]
    }

    /**
     * Method is used to provide current year.
     */
    fun getCurrentYear(): Int {
        LoggerUtils.debug(TAG, "getCurrentYear()")
        return Calendar.getInstance(Locale.US).get(Calendar.YEAR)
    }

    /**
     * Method is used to provide current week of month.
     */
    fun getCurrentWeekOfMonth(): Int {
        LoggerUtils.debug(TAG, "getCurrentWeekOfMonth()")
        return Calendar.getInstance(Locale.US).get(Calendar.WEEK_OF_MONTH)
    }

    /**
     * Method is used to provide current day of month.
     */
    fun getCurrentDayOfMonth(): Int {
        LoggerUtils.debug(TAG, "getCurrentDayOfMonth()")
        return Calendar.getInstance(Locale.US).get(Calendar.DAY_OF_MONTH)
    }

    /**
     * Method is used to provide current day of year.
     */
    fun getCurrentDayOfYear(): Int {
        LoggerUtils.debug(TAG, "getCurrentDayOfYear()")
        return Calendar.getInstance(Locale.US).get(Calendar.DAY_OF_MONTH)
    }

    /**
     * Method is used to provide current day of week.
     */
    fun getCurrentDayOfWeek(): Int {
        LoggerUtils.debug(TAG, "getCurrentDayOfWeek()")
        return Calendar.getInstance(Locale.US).get(Calendar.DAY_OF_WEEK)
    }

    /**
     * Method is used to provide current day name of week i.e Sunday, Monday etc... .
     */
    fun getCurrentDayOfWeekName(): String {
        LoggerUtils.debug(TAG, "getCurrentDayOfWeekName()")
        val dfs = DateFormatSymbols(Locale.US)
        return dfs.weekdays[getCurrentDayOfWeek()]
    }

    /**
     * Method is used to provide current short day name of week i.e Sunday, Monday etc... .
     */
    fun getCurrentDayOfWeekShortName(): String {
        LoggerUtils.debug(TAG, "getCurrentDayOfWeekShortName()")
        val dfs = DateFormatSymbols(Locale.US)
        return dfs.shortWeekdays[getCurrentDayOfWeek()]
    }

    /**
     * Method is used to provide current hour of day i.e 10:00 PM = 10.
     */
    fun getCurrentHour(): Int {
        LoggerUtils.debug(TAG, "getCurrentHour()")
        return Calendar.getInstance(Locale.US).get(Calendar.HOUR)
    }

    /**
     * Method is used to provide current hour of day i.e 10:00 PM = 22.
     */
    fun getCurrentHourOfDay(): Int {
        LoggerUtils.debug(TAG, "getCurrentHourOfDay()")
        return Calendar.getInstance(Locale.US).get(Calendar.HOUR_OF_DAY)
    }

    /**
     * Method is used to provide current minute.
     */
    fun getCurrentMinute(): Int {
        LoggerUtils.debug(TAG, "getCurrentMinute()")
        return Calendar.getInstance(Locale.US).get(Calendar.MINUTE)
    }

    /**
     * Method is used to provide current second.
     */
    fun getCurrentSecond(): Int {
        LoggerUtils.debug(TAG, "getCurrentSecond()")
        return Calendar.getInstance(Locale.US).get(Calendar.SECOND)
    }

    /**
     * Method is used to provide current millisecond.
     */
    fun getCurrentMilliSecond(): Int {
        LoggerUtils.debug(TAG, "getCurrentMilliSecond()")
        return Calendar.getInstance(Locale.US).get(Calendar.MILLISECOND)
    }
}
