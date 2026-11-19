package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity : BaseActivity() {

    private var profileUser by mutableStateOf<User?>(null)
    private var viewerIsAdmin by mutableStateOf(false)
    private var currentUserId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val avatars = intArrayOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
        )

        setContent {
            AppTheme(darkTheme = isDarkTheme) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .verticalScroll(rememberScrollState())
                        .padding(25.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.profile),
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    val user = profileUser

                    if (user != null) {
                        Image(
                            painter = painterResource(
                                avatars.getOrElse(user.avatarIndex) {
                                    R.drawable.avatar1
                                }
                            ),
                            contentDescription = stringResource(R.string.avatar),
                            modifier = Modifier.size(100.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = stringResource(R.string.profile_name, user.name),
                            fontSize = 20.sp
                        )

                        Text(
                            text = stringResource(R.string.profile_login, user.login),
                            fontSize = 20.sp
                        )

                        Text(
                            text = stringResource(R.string.profile_email, user.email),
                            fontSize = 20.sp
                        )

                        Text(
                            text = stringResource(
                                R.string.profile_birth_date,
                                user.birthDate
                            ),
                            fontSize = 20.sp
                        )

                        Text(
                            text = stringResource(
                                R.string.profile_gender,
                                user.gender
                            ),
                            fontSize = 20.sp
                        )

                        if (user.isAdmin) {
                            Text(stringResource(R.string.admin_role))
                        }

                        if (viewerIsAdmin && !user.isAdmin &&
                            user.id != currentUserId
                        ) {
                            Button(
                                onClick = {
                                    val database = DatabaseHandler(
                                        this@ProfileActivity
                                    )

                                    val currentUser = database.getUserById(
                                        currentUserId
                                    )

                                    val success =
                                        currentUser?.isAdmin == true &&
                                                database.makeAdmin(user.id)

                                    if (success) {
                                        profileUser = database.getUserById(user.id)
                                    }

                                    database.close()

                                    Toast.makeText(
                                        this@ProfileActivity,
                                        if (success) {
                                            R.string.admin_assigned
                                        } else {
                                            R.string.admin_assign_error
                                        },
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(stringResource(R.string.make_admin))
                            }
                        }
                    } else {
                        Text(stringResource(R.string.user_not_found))
                    }

                    Button(
                        onClick = { finish() },
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

        currentUserId = getSharedPreferences("session", MODE_PRIVATE)
            .getInt("userId", -1)

        val profileUserId = intent.getIntExtra(
            "profileUserId",
            currentUserId
        )

        val database = DatabaseHandler(this)

        val currentUser = database.getUserById(currentUserId)

        viewerIsAdmin = currentUser?.isAdmin == true

        profileUser = if (
            currentUser != null &&
            (profileUserId == currentUserId || currentUser.isAdmin)
        ) {
            database.getUserById(profileUserId)
        } else {
            null
        }

        database.close()
    }
}