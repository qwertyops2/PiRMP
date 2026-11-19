package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class UsersActivity : BaseActivity() {

    private var users by mutableStateOf<List<User>>(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme(darkTheme = isDarkTheme) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.btn_admin),
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(users, key = { it.id }) { user ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        val intent = Intent(
                                            this@UsersActivity,
                                            ProfileActivity::class.java
                                        )

                                        intent.putExtra(
                                            "profileUserId",
                                            user.id
                                        )

                                        startActivity(intent)
                                    }
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = user.name,
                                    fontSize = 20.sp
                                )

                                Text(
                                    text = user.login,
                                    fontSize = 14.sp
                                )

                                if (user.isAdmin) {
                                    Text(
                                        text = stringResource(R.string.admin_role),
                                        fontSize = 14.sp
                                    )
                                }
                            }

                            HorizontalDivider()
                        }
                    }

                    Button(
                        onClick = {
                            Log.i("UsersActivity", "Нажата кнопка Назад")
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

    override fun onResume() {
        super.onResume()

        val userId = getSharedPreferences("session", MODE_PRIVATE)
            .getInt("userId", -1)

        val database = DatabaseHandler(this)
        val currentUser = database.getUserById(userId)

        if (currentUser?.isAdmin == true) {
            users = database.getAllUsers()

            Log.i(
                "UsersActivity",
                "Загружено пользователей: ${users.size}"
            )
        }

        database.close()

        if (currentUser?.isAdmin != true) {
            finish()
        }
    }
}