package com.android.mvvmandroidlib.utills

import android.content.Context

/**
 * This util class is used to provide methods to store and read data by SharedPreference.
 * It will store data in app local data. It only store primitive type data.
 * This class is declared as object('Singleton').
 *
 * @author Shubham Chauhan
 */
object SharedPreferencesUtils {

    private const val FILE_NAME: String = "AndroidMVVMSharedPreferences"

    /**
     * Set a String value in the preferences editor.
     *
     * @param context context
     * @param key The name of the preference to modify.
     * @param value The new value for the preference. Passing 'null'
     *    for this argument is equivalent to calling [.remove(String)] with
     *    this key.
     * @param fileName file name.
     */
    fun setString(context: Context, key: String, value: String, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Set an Int value in the preferences editor.
     *
     * @param context context
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     * @param fileName file name.
     */
    fun setInt(context: Context, key: String, value: Int, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    /**
     * Set a Long value in the preferences editor.
     *
     * @param context context
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     * @param fileName file name.
     */
    fun setLong(context: Context, key: String, value: Long, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    /**
     * Set a Boolean value in the preferences editor.
     *
     * @param context context
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     * @param fileName file name.
     */
    fun setBoolean(context: Context, key: String, value: Boolean, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    /**
     * Set a Float value in the preferences editor.
     *
     * @param context context
     * @param key The name of the preference to modify.
     * @param value The new value for the preference.
     * @param fileName file name.
     */
    fun setFloat(context: Context, key: String, value: Float, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    /**
     * Set a set of String values in the preferences editor.
     *
     * @param context context
     * @param key The name of the preference to modify.
     * @param values The set of new values for the preference.  Passing 'null'
     *    for this argument is equivalent to calling [.remove(String)] with
     *    this key.
     * @param fileName file name.
     */
    fun setStringSet(
        context: Context,
        key: String,
        values: Set<String>,
        fileName: String = FILE_NAME
    ) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putStringSet(key, values)
        editor.apply()
    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param context context
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param fileName file name
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a String.
     *
     * @throws ClassCastException
     */
    fun getString(
        context: Context,
        key: String,
        defaultValue: String? = null,
        fileName: String = FILE_NAME
    ): String? {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getString(key, defaultValue)
    }

    /**
     * Retrieve an Int value from the preferences.
     *
     * @param context context
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param fileName file name
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     *
     * @throws ClassCastException
     */
    fun getInt(
        context: Context,
        key: String,
        defaultValue: Int = 0,
        fileName: String = FILE_NAME
    ): Int {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getInt(key, defaultValue)
    }

    /**
     * Retrieve a Float value from the preferences.
     *
     * @param context context
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param fileName file name
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     *
     * @throws ClassCastException
     */
    fun getFloat(
        context: Context,
        key: String,
        defaultValue: Float = 0.0f,
        fileName: String = FILE_NAME
    ): Float {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getFloat(key, defaultValue)
    }

    /**
     * Retrieve a Boolean value from the preferences.
     *
     * @param context context
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param fileName file name
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     *
     * @throws ClassCastException
     */
    fun getBoolean(
        context: Context,
        key: String,
        defaultValue: Boolean = false,
        fileName: String = FILE_NAME
    ): Boolean {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, defaultValue)
    }

    /**
     * Retrieve a set of String values from the preferences.
     *
     * @param context context
     * @param key The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @param fileName file name
     *
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * an int.
     *
     * @throws ClassCastException
     */
    fun getStringSet(
        context: Context,
        key: String,
        defaultValue: Set<String>? = null,
        fileName: String = FILE_NAME
    ): Set<String>? {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getStringSet(key, defaultValue)
    }
}
