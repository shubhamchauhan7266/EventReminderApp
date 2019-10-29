package com.event.reminder.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.event.reminder.R
import com.event.reminder.ui.dashboard.DashboardActivity
import com.event.reminder.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (/*EventReminderSharedPrefUtils.isUserLoggedIn()*/true) {
            startActivity(Intent(this@SplashActivity, DashboardActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }
}
