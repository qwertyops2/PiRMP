package com.example.myapplication

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<Button>(R.id.buttonStart).setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
        }

        findViewById<Button>(R.id.buttonProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<Button>(R.id.buttonSettings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        findViewById<Button>(R.id.buttonReport).setOnClickListener {

            val options = arrayOf(
                getString(R.string.contact_email),
                getString(R.string.contact_phone)
            )

            AlertDialog.Builder(this)
                .setTitle(R.string.choose_contact)
                .setItems(options) { _, which ->

                    if (which == 0) {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:tak_nazivaemay_podderjka@gmail.com")
                        }

                        try {
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(
                                this,
                                R.string.no_mail_app,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:+71234567890")
                        }

                        try {
                            startActivity(intent)
                        } catch (e: ActivityNotFoundException) {
                            Toast.makeText(
                                this,
                                R.string.no_phone_app,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                .setNegativeButton(R.string.cancel, null)
                .show()
        }

        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            finishAffinity()
        }
    }
}