package com.example.myapplication

import android.app.Activity
import android.os.Bundle

open class BaseActivity : Activity() {

    private var currentDarkMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        currentDarkMode = prefs.getBoolean("dark_mode", false)

        if (currentDarkMode) {
            setTheme(R.style.AppThemeNight)
        } else {
            setTheme(R.style.AppThemeDay)
        }

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val darkMode = prefs.getBoolean("dark_mode", false)

        if (darkMode != currentDarkMode) {
            recreate()
        }
    }
}