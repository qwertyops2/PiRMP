package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)

        val name = prefs.getString("name", "") ?: ""
        val login = prefs.getString("login", "") ?: ""
        val email = prefs.getString("email", "") ?: ""

        findViewById<TextView>(R.id.textName).text =
            getString(R.string.profile_name, name)

        findViewById<TextView>(R.id.textLogin).text =
            getString(R.string.profile_login, login)

        findViewById<TextView>(R.id.textEmail).text =
            getString(R.string.profile_email, email)

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }
}