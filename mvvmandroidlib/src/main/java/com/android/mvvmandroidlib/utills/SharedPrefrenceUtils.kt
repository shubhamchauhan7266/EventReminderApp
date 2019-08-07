package com.android.mvvmandroidlib.utills

import android.app.Activity
import android.content.Context


object SharedPrefrenceUtils {

    private const val FILE_NAME: String = "AndroidMVVMSharedPreferences"

    fun setString(context: Activity, key: String, value: String, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setInt(context: Activity, key: String, value: Int, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun setLong(context: Activity, key: String, value: Long, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun setBoolean(context: Activity, key: String, value: Boolean, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun setFloat(context: Activity, key: String, value: Float, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun setStringSet(context: Activity, key: String, value: Set<String>, fileName: String = FILE_NAME) {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun getString(context: Activity, key: String, defaultValue: String? = null, fileName: String = FILE_NAME): String? {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getString(key, defaultValue)
    }

    fun getInt(context: Activity, key: String, defaultValue: Int = 0, fileName: String = FILE_NAME): Int {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getInt(key, defaultValue)
    }

    fun getFloat(context: Activity, key: String, defaultValue: Float = 0.0f, fileName: String = FILE_NAME): Float {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getFloat(key, defaultValue)
    }

    fun getBoolean(
        context: Activity,
        key: String,
        defaultValue: Boolean = false,
        fileName: String = FILE_NAME
    ): Boolean {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(key, defaultValue)
    }

    fun getStringSet(
        context: Activity,
        key: String,
        defaultValue: Set<String>? = null,
        fileName: String = FILE_NAME
    ): Set<String>? {
        val sharedPref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        return sharedPref.getStringSet(key, defaultValue)
    }
}
