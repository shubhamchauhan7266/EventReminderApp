package com.event.reminder.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

public abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setInitialData()
    }

    abstract fun setInitialData()
}