package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistrationActivity : BaseActivity() {

    private lateinit var editName: EditText
    private lateinit var editLogin: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        Log.i("RegistrationActivity", "onCreate")

        editName = findViewById(R.id.editName)
        editLogin = findViewById(R.id.editLogin)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)

        if (savedInstanceState != null) {
            editName.setText(savedInstanceState.getString("name"))
            editLogin.setText(savedInstanceState.getString("login"))
            editEmail.setText(savedInstanceState.getString("email"))
            editPassword.setText(savedInstanceState.getString("password"))
        }

        findViewById<Button>(R.id.buttonRegister).setOnClickListener {

            val name = editName.text.toString()
            val login = editLogin.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            if (name.isBlank() || login.isBlank() ||
                email.isBlank() || password.isBlank()) {

                Toast.makeText(
                    this,
                    R.string.empty_fields,
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                getSharedPreferences("user", MODE_PRIVATE)
                    .edit()
                    .putString("name", name)
                    .putString("login", login)
                    .putString("email", email)
                    .apply()

                Toast.makeText(
                    this,
                    R.string.registration_success,
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("name", editName.text.toString())
        outState.putString("login", editLogin.text.toString())
        outState.putString("email", editEmail.text.toString())
        outState.putString("password", editPassword.text.toString())
    }

    override fun onStart() {
        super.onStart()
        Log.i("RegistrationActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("RegistrationActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("RegistrationActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("RegistrationActivity", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("RegistrationActivity", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("RegistrationActivity", "onDestroy")
    }
}