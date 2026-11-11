package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var users by remember {
                mutableStateOf(
                    listOf(
                        getString(R.string.demo_user, 1),
                        getString(R.string.demo_user, 2),
                        getString(R.string.demo_user, 3)
                    )
                )
            }

            AppTheme(darkTheme = isDarkTheme) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.users_title),
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(users) { user ->
                            Text(
                                text = user,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            val newUser = getString(
                                R.string.demo_user,
                                users.size + 1
                            )

                            users = users + newUser

                            Log.i(
                                "UsersActivity",
                                "Добавлен пользователь: $newUser"
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.add_user))
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
}
