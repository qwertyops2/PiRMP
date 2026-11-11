package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class RegistrationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i("RegistrationActivity", "onCreate")

        val avatars = intArrayOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
        )

        setContent {
            var name by rememberSaveable { mutableStateOf("") }
            var login by rememberSaveable { mutableStateOf("") }
            var email by rememberSaveable { mutableStateOf("") }
            var birthDate by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var repeatPassword by rememberSaveable { mutableStateOf("") }
            var genderChoice by rememberSaveable { mutableIntStateOf(0) }
            var avatarIndex by rememberSaveable { mutableIntStateOf(0) }
            var passwordMismatch by rememberSaveable { mutableStateOf(false) }

            AppTheme(darkTheme = isDarkTheme) {
                AppTheme(darkTheme = isDarkTheme) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .verticalScroll(rememberScrollState())
                            .padding(25.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.btn_registration),
                            fontSize = 30.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Image(
                            painter = painterResource(avatars[avatarIndex]),
                            contentDescription = stringResource(R.string.avatar),
                            modifier = Modifier
                                .size(100.dp)
                                .clickable {
                                    avatarIndex = (avatarIndex + 1) % avatars.size

                                    Log.i(
                                        "RegistrationActivity",
                                        "Выбран аватар: $avatarIndex"
                                    )
                                },
                            contentScale = ContentScale.Crop
                        )

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text(stringResource(R.string.name)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = login,
                            onValueChange = { login = it },
                            label = { Text(stringResource(R.string.login)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(stringResource(R.string.email)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = birthDate,
                            onValueChange = { birthDate = it },
                            label = { Text(stringResource(R.string.birth_date)) },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Row(
                                modifier = Modifier.clickable {
                                    genderChoice = 1
                                },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = genderChoice == 1,
                                    onClick = { genderChoice = 1 }
                                )

                                Text(stringResource(R.string.male))
                            }

                            Row(
                                modifier = Modifier.clickable {
                                    genderChoice = 2
                                },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = genderChoice == 2,
                                    onClick = { genderChoice = 2 }
                                )

                                Text(stringResource(R.string.female))
                            }
                        }

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(stringResource(R.string.password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = repeatPassword,
                            onValueChange = {
                                repeatPassword = it
                                passwordMismatch = false
                            },
                            label = { Text(stringResource(R.string.confirm_password)) },
                            visualTransformation = PasswordVisualTransformation(),
                            isError = passwordMismatch,
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        if (passwordMismatch) {
                            Text(
                                text = stringResource(R.string.passwords_do_not_match),
                                color = MaterialTheme.colorScheme.error
                            )
                        }

                        Button(
                            onClick = {
                                Log.i("RegistrationActivity","Нажата кнопка Регистрация")

                                val gender = when (genderChoice) {
                                    1 -> getString(R.string.male)
                                    2 -> getString(R.string.female)
                                    else -> ""
                                }

                                if (name.isBlank() || login.isBlank() ||
                                    email.isBlank() || password.isBlank() ||
                                    repeatPassword.isBlank() || birthDate.isBlank() ||
                                    gender.isBlank()
                                ) {
                                    Log.i(
                                        "RegistrationActivity",
                                        "Не все поля заполнены"
                                    )

                                    Toast.makeText(
                                        this@RegistrationActivity,
                                        R.string.empty_fields,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else if (password != repeatPassword) {

                                    passwordMismatch = true

                                    Log.i(
                                        "RegistrationActivity",
                                        "Пароли не совпадают"
                                    )

                                    Toast.makeText(
                                        this@RegistrationActivity,
                                        R.string.passwords_do_not_match,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {

                                    getSharedPreferences("user", MODE_PRIVATE)
                                        .edit()
                                        .putInt("avatarIndex", avatarIndex)
                                        .putString("name", name)
                                        .putString("login", login)
                                        .putString("email", email)
                                        .putString("password", password)
                                        .putString("birthDate", birthDate.trim())
                                        .putString("gender", gender)
                                        .apply()

                                    Log.i(
                                        "RegistrationActivity",
                                        "Регистрация выполнена"
                                    )

                                    Toast.makeText(
                                        this@RegistrationActivity,
                                        R.string.registration_success,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(
                                        this@RegistrationActivity,
                                        MenuActivity::class.java
                                    )
                                    startActivity(intent)
                                    finish()
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(stringResource(R.string.btn_registration))
                        }

                        Button(
                            onClick = {
                                Log.i(
                                    "RegistrationActivity",
                                    "Нажата кнопка Назад"
                                )
                                finish()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(stringResource(R.string.btn_back))
                        }
                    }
                }
            }
        }
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
