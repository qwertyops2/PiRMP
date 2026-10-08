package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class HelloActivity : BaseActivity() {

    private lateinit var editLogin: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helloact)

        Log.i("HelloActivity", "onCreate")

        editLogin = findViewById(R.id.editLogin)
        editPassword = findViewById(R.id.editPassword)

        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonRegistration).setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            finish()
        }

        if (savedInstanceState != null) {
            editLogin.setText(savedInstanceState.getString("login"))
            editPassword.setText(savedInstanceState.getString("password"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("login", editLogin.text.toString())
        outState.putString("password", editPassword.text.toString())
    }

    override fun onStart() {
        super.onStart()
        Log.i("HelloActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("HelloActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("HelloActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("HelloActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("HelloActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("HelloActivity", "onDestroy")
    }
}