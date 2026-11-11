package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.mutableStateOf

open class BaseActivity : ComponentActivity() {

    private val darkThemeState = mutableStateOf(false)

    protected val isDarkTheme: Boolean get() = darkThemeState.value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readSavedTheme()
    }

    override fun onResume() {
        super.onResume()
        readSavedTheme()
    }

    protected fun setDarkTheme(enabled: Boolean) {
        getSharedPreferences("settings", MODE_PRIVATE)
            .edit()
            .putBoolean("dark_mode", enabled)
            .apply()
        darkThemeState.value = enabled
    }

    private fun readSavedTheme() {
        darkThemeState.value = getSharedPreferences("settings", MODE_PRIVATE)
            .getBoolean("dark_mode", false)
    }
}