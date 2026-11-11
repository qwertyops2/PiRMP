package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class HelloActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("HelloActivity", "onCreate")

        setContent {
            var login by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }

            AppTheme(darkTheme = isDarkTheme) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatar1),
                        contentDescription = stringResource(R.string.app_name),
                        modifier = Modifier.size(100.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )

                    Text(
                        text = stringResource(R.string.welcome),
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )

                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(stringResource(R.string.login)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(R.string.password)) },
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Button(
                        onClick = {
                            val prefs = getSharedPreferences("user", MODE_PRIVATE)
                            val savedLogin = prefs.getString("login", null)
                            val savedPassword = prefs.getString("password", null)

                            if (login.isNotEmpty() && password.isNotEmpty() &&
                                login == savedLogin && password == savedPassword) {

                                val intent = Intent(this@HelloActivity, MenuActivity::class.java)
                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(
                                    this@HelloActivity,
                                    R.string.invalid_credentials,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.btn_login))
                    }

                    Button(
                        onClick = {
                            val intent = Intent(this@HelloActivity, RegistrationActivity::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.btn_registration))
                    }

                    Button(
                        onClick = {
                            finish()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.btn_exit))
                    }
                }
            }
        }
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