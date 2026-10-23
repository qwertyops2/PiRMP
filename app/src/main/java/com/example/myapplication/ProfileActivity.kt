package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)

        val avatars = intArrayOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
        )

        val avatarIndex = prefs.getInt("avatarIndex", 0)

        val name = prefs.getString("name", "") ?: ""
        val login = prefs.getString("login", "") ?: ""
        val email = prefs.getString("email", "") ?: ""
        val birthDate = prefs.getString("birthDate", "") ?: ""
        val gender = prefs.getString("gender", "") ?: ""

        findViewById<ImageView>(R.id.imageAvatar)
            .setImageResource(avatars[avatarIndex])

        findViewById<TextView>(R.id.textName).text =
            getString(R.string.profile_name, name)

        findViewById<TextView>(R.id.textLogin).text =
            getString(R.string.profile_login, login)

        findViewById<TextView>(R.id.textEmail).text =
            getString(R.string.profile_email, email)

        findViewById<TextView>(R.id.textBirthDate).text =
            getString(R.string.profile_birth_date, birthDate)

        findViewById<TextView>(R.id.textGender).text =
            getString(R.string.profile_gender, gender)

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }
}