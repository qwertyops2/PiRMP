package com.example.myapplication

data class User(
    val id: Int = 0,
    val name: String,
    val login: String,
    val email: String,
    val password: String,
    val birthDate: String,
    val gender: String,
    val avatarIndex: Int,
    val isAdmin: Boolean = false
)