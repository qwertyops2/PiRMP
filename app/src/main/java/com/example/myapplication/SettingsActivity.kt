package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Switch

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        val switchTheme = findViewById<Switch>(R.id.switchTheme)

        switchTheme.isChecked = prefs.getBoolean("dark_mode", false)

        switchTheme.setOnCheckedChangeListener { _, checked ->
            prefs.edit()
                .putBoolean("dark_mode", checked)
                .apply()

            recreate()
        }

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }
}