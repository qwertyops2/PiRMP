
package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Users.db"
        private const val DATABASE_VERSION = 1
    }

    private val u = DBContract.UserEntry

    // Создание таблицы при первом открытии базы
    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE ${u.TABLE_NAME} (
                ${u.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${u.COLUMN_NAME} TEXT NOT NULL,
                ${u.COLUMN_LOGIN} TEXT NOT NULL UNIQUE,
                ${u.COLUMN_EMAIL} TEXT NOT NULL,
                ${u.COLUMN_PASSWORD} TEXT NOT NULL,
                ${u.COLUMN_BIRTH_DATE} TEXT NOT NULL,
                ${u.COLUMN_GENDER} TEXT NOT NULL,
                ${u.COLUMN_AVATAR_INDEX} INTEGER NOT NULL,
                ${u.COLUMN_IS_ADMIN} INTEGER NOT NULL DEFAULT 0
            )
        """.trimIndent()

        db.execSQL(sql)
    }

    // Обновление структуры базы при смене версии
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS ${u.TABLE_NAME}")
        onCreate(db)
    }

    // Регистрация пользователя
    fun addUser(user: User): Long {
        val db = writableDatabase

        // Проверяем, есть ли уже пользователи
        val firstUser = db.rawQuery(
            "SELECT COUNT(*) FROM ${u.TABLE_NAME}",
            null
        ).use { cursor ->
            cursor.moveToFirst()
            cursor.getInt(0) == 0
        }

        val values = ContentValues()

        values.put(u.COLUMN_NAME, user.name)
        values.put(u.COLUMN_LOGIN, user.login)
        values.put(u.COLUMN_EMAIL, user.email)
        values.put(u.COLUMN_PASSWORD, user.password)
        values.put(u.COLUMN_BIRTH_DATE, user.birthDate)
        values.put(u.COLUMN_GENDER, user.gender)
        values.put(u.COLUMN_AVATAR_INDEX, user.avatarIndex)
        values.put(u.COLUMN_IS_ADMIN, if (firstUser) 1 else 0)

        return db.insert(u.TABLE_NAME, null, values)
    }

    // Получение всех зарегистрированных пользователей
    fun getAllUsers(): List<User> {
        val users = mutableListOf<User>()

        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${u.TABLE_NAME} ORDER BY ${u.COLUMN_ID}",
            null
        )

        cursor.use {
            while (it.moveToNext()) {
                users.add(cursorToUser(it))
            }
        }

        return users
    }

    // Поиск пользователя для авторизации
    fun getUserByCredentials(
        login: String,
        password: String
    ): User? {
        val cursor = readableDatabase.query(
            u.TABLE_NAME,
            null,
            "${u.COLUMN_LOGIN} = ? AND ${u.COLUMN_PASSWORD} = ?",
            arrayOf(login, password),
            null,
            null,
            null
        )

        cursor.use {
            return if (it.moveToFirst()) cursorToUser(it) else null
        }
    }

    // Получение пользователя по его ID
    fun getUserById(id: Int): User? {
        val cursor = readableDatabase.query(
            u.TABLE_NAME,
            null,
            "${u.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        cursor.use {
            return if (it.moveToFirst()) cursorToUser(it) else null
        }
    }

    // Назначение пользователя администратором
    fun makeAdmin(userId: Int): Boolean {
        val values = ContentValues()
        values.put(u.COLUMN_IS_ADMIN, 1)

        val updatedRows = writableDatabase.update(
            u.TABLE_NAME,
            values,
            "${u.COLUMN_ID} = ?",
            arrayOf(userId.toString())
        )

        return updatedRows > 0
    }

    // Преобразование строки БД в объект User
    private fun cursorToUser(cursor: Cursor): User {
        return User(
            id = cursor.getInt(
                cursor.getColumnIndexOrThrow(u.COLUMN_ID)
            ),
            name = cursor.getString(
                cursor.getColumnIndexOrThrow(u.COLUMN_NAME)
            ),
            login = cursor.getString(
                cursor.getColumnIndexOrThrow(u.COLUMN_LOGIN)
            ),
            email = cursor.getString(
                cursor.getColumnIndexOrThrow(u.COLUMN_EMAIL)
            ),
            password = cursor.getString(
                cursor.getColumnIndexOrThrow(u.COLUMN_PASSWORD)
            ),
            birthDate = cursor.getString(
                cursor.getColumnIndexOrThrow(u.COLUMN_BIRTH_DATE)
            ),
            gender = cursor.getString(
                cursor.getColumnIndexOrThrow(u.COLUMN_GENDER)
            ),
            avatarIndex = cursor.getInt(
                cursor.getColumnIndexOrThrow(u.COLUMN_AVATAR_INDEX)
            ),
            isAdmin = cursor.getInt(
                cursor.getColumnIndexOrThrow(u.COLUMN_IS_ADMIN)
            ) == 1
        )
    }
}
