package com.example.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val prefs = getSharedPreferences("user", MODE_PRIVATE)

        val avatars = intArrayOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3
        )

        val avatarIndex = prefs.getInt("avatarIndex", 0)

        val name = prefs.getString("name", "") ?: ""
        val login = prefs.getString("login", "") ?: ""
        val email = prefs.getString("email", "") ?: ""
        val birthDate = prefs.getString("birthDate", "") ?: ""
        val gender = prefs.getString("gender", "") ?: ""

        setContent {
            AppTheme(darkTheme = isDarkTheme) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .verticalScroll(rememberScrollState())
                        .padding(25.dp),
                    verticalArrangement = Arrangement.spacedBy(
                        14.dp,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.profile),
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Image(
                        painter = painterResource(
                            id = avatars[avatarIndex]
                        ),
                        contentDescription = stringResource(R.string.avatar),
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = stringResource(R.string.profile_name, name),
                        fontSize = 20.sp
                    )

                    Text(
                        text = stringResource(R.string.profile_login, login),
                        fontSize = 20.sp
                    )

                    Text(
                        text = stringResource(R.string.profile_email, email),
                        fontSize = 20.sp
                    )

                    Text(
                        text = stringResource(R.string.profile_birth_date, birthDate),
                        fontSize = 20.sp
                    )

                    Text(
                        text = stringResource(R.string.profile_gender, gender),
                        fontSize = 20.sp
                    )

                    Button(
                        onClick = { finish() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.btn_back)
                        )
                    }
                }
            }
        }
    }
}