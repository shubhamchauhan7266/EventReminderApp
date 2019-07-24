package com.android.mvvmandroidlib

import android.app.Application

open class BaseApplication : Application() {

    companion object {

        private var instance: BaseApplication? = null
        fun getInstance(): BaseApplication {

            if (instance == null) {
                instance = BaseApplication()
            }
            return instance as BaseApplication
        }
    }
}