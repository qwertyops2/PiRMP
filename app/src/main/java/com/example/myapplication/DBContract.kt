package com.example.myapplication

import android.provider.BaseColumns

object DBContract {

    object UserEntry : BaseColumns {

        const val TABLE_NAME = "users"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_LOGIN = "login"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_BIRTH_DATE = "birthDate"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_AVATAR_INDEX = "avatarIndex"
        const val COLUMN_IS_ADMIN = "isAdmin"
    }
}