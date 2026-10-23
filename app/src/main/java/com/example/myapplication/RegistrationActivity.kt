package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Toast

class RegistrationActivity : BaseActivity() {

    private lateinit var editName: EditText
    private lateinit var editLogin: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editRepeatPassword: EditText
    private lateinit var editBirthDate: EditText
    private lateinit var radioGender: RadioGroup
    private lateinit var imageAvatar: ImageView

    private val avatars = intArrayOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3
    )

    private var avatarIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        Log.i("RegistrationActivity", "onCreate")

        editName = findViewById(R.id.editName)
        editLogin = findViewById(R.id.editLogin)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        editRepeatPassword = findViewById(R.id.editRepeatPassword)
        editBirthDate = findViewById(R.id.editBirthDate)
        radioGender = findViewById(R.id.radioGender)

        if (savedInstanceState != null) {
            editName.setText(savedInstanceState.getString("name"))
            editLogin.setText(savedInstanceState.getString("login"))
            editEmail.setText(savedInstanceState.getString("email"))
            editPassword.setText(savedInstanceState.getString("password"))
            editRepeatPassword.setText(
                savedInstanceState.getString("repeatPassword")
            )

            editBirthDate.setText(
                savedInstanceState.getString("birthDate")
            )

            val genderId = savedInstanceState.getInt("genderId", -1)

            if (genderId != -1) {
                radioGender.check(genderId)
            }
        }

        findViewById<Button>(R.id.buttonRegister).setOnClickListener {

            Log.i("RegistrationActivity", "Нажата кнопка Регистрация")

            val name = editName.text.toString()
            val login = editLogin.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            val repeatPassword = editRepeatPassword.text.toString()
            val birthDate = editBirthDate.text.toString().trim()

            val gender = when (radioGender.checkedRadioButtonId) {
                R.id.radioMale -> getString(R.string.male)
                R.id.radioFemale -> getString(R.string.female)
                else -> ""
            }

            if (name.isBlank() || login.isBlank() ||
                email.isBlank() || password.isBlank() ||
                repeatPassword.isBlank() || birthDate.isBlank() ||
                gender.isBlank()) {

                Log.i("RegistrationActivity", "Не все поля заполнены")

                Toast.makeText(
                    this,
                    R.string.empty_fields,
                    Toast.LENGTH_SHORT
                ).show()

            } else if (password != repeatPassword) {

                editRepeatPassword.error =
                    getString(R.string.passwords_do_not_match)

                Log.i("RegistrationActivity", "Пароли не совпадают")

                Toast.makeText(
                    this,
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
                    .putString("birthDate", birthDate)
                    .putString("gender", gender)
                    .apply()

                Log.i("RegistrationActivity", "Регистрация выполнена")

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

            Log.i("RegistrationActivity", "Нажата кнопка Назад")
            finish()
        }

        imageAvatar = findViewById(R.id.imageAvatar)

        avatarIndex = savedInstanceState?.getInt("avatarIndex") ?: 0
        imageAvatar.setImageResource(avatars[avatarIndex])

        imageAvatar.setOnClickListener {
            avatarIndex = (avatarIndex + 1) % avatars.size
            imageAvatar.setImageResource(avatars[avatarIndex])

            Log.i("RegistrationActivity", "Выбран аватар: $avatarIndex")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString("name", editName.text.toString())
        outState.putString("login", editLogin.text.toString())
        outState.putString("email", editEmail.text.toString())
        outState.putString("password", editPassword.text.toString())
        outState.putString("repeatPassword", editRepeatPassword.text.toString())
        outState.putString("birthDate", editBirthDate.text.toString())
        outState.putInt("genderId", radioGender.checkedRadioButtonId)
        outState.putInt("avatarIndex", avatarIndex)
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