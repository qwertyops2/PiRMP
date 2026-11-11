package com.example.myapplication

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var showContactDialog by remember {
                mutableStateOf(false)
            }

            AppTheme(darkTheme = isDarkTheme) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.menu),
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            startActivity(
                                Intent(this@MenuActivity, StartActivity::class.java)
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.btn_start))
                    }

                    Button(
                        onClick = {
                            startActivity(
                                Intent(this@MenuActivity, ProfileActivity::class.java)
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.btn_profile))
                    }

                    Button(
                        onClick = {
                            startActivity(
                                Intent(this@MenuActivity, UsersActivity::class.java)
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.menu_users))
                    }

                    Button(
                        onClick = {
                            startActivity(
                                Intent(this@MenuActivity, SettingsActivity::class.java)
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.btn_settings))
                    }

                    Button(
                        onClick = {
                            showContactDialog = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.btn_report))
                    }

                    Button(
                        onClick = { finishAffinity() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.btn_exit))
                    }
                }

                if (showContactDialog) {
                    AlertDialog(
                        onDismissRequest = {
                            showContactDialog = false
                        },
                        title = {
                            Text(stringResource(R.string.choose_contact))
                        },
                        text = {
                            Column {

                                TextButton(
                                    onClick = {
                                        showContactDialog = false

                                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                                            data = Uri.parse(
                                                "mailto:tak_nazivaemay_podderjka@gmail.com"
                                            )
                                        }

                                        try {
                                            startActivity(intent)
                                        } catch (e: ActivityNotFoundException) {
                                            Toast.makeText(
                                                this@MenuActivity,
                                                R.string.no_mail_app,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                ) {
                                    Text(stringResource(R.string.contact_email))
                                }

                                TextButton(
                                    onClick = {
                                        showContactDialog = false

                                        val intent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:+71234567890")
                                        }

                                        try {
                                            startActivity(intent)
                                        } catch (e: ActivityNotFoundException) {
                                            Toast.makeText(
                                                this@MenuActivity,
                                                R.string.no_phone_app,
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                ) {
                                    Text(stringResource(R.string.contact_phone))
                                }
                            }
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    showContactDialog = false
                                }
                            ) {
                                Text(stringResource(R.string.cancel))
                            }
                        }
                    )
                }
            }
        }
    }
}