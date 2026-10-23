package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class UsersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val users = ArrayList<String>()

        users.add(getString(R.string.demo_user, 1))
        users.add(getString(R.string.demo_user, 2))
        users.add(getString(R.string.demo_user, 3))

        val usersList = findViewById<ListView>(R.id.usersList)

        val adapter = ArrayAdapter(
            this,
            R.layout.item,
            R.id.itemContent,
            users
        )

        usersList.adapter = adapter

        findViewById<Button>(R.id.buttonAddUser).setOnClickListener {
            val newUser = getString(R.string.demo_user, users.size + 1)
            users.add(newUser)
            adapter.notifyDataSetChanged()

            Log.i("UsersActivity", "Добавлен пользователь: $newUser")
        }

        findViewById<Button>(R.id.buttonBack).setOnClickListener {
            Log.i("UsersActivity", "Нажата кнопка Назад")
            finish()
        }
    }
}