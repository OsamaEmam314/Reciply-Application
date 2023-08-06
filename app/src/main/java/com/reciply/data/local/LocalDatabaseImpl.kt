package com.reciply.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.reciply.data.models.Meal
import com.reciply.db.UsersDatabase
import com.reciply.data.models.User
import com.reciply.db.UsersDao


class LocalDatabaseImpl(context: Context): LocalDatabase {
    private lateinit var dao: UsersDao
    init {
        val db = UsersDatabase.getInstance(context)
        dao = db.getDao()
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }

    override suspend fun getUserByEmail(userEmail: String): LiveData<User?> {
        return dao.getUserByEmail(userEmail)
    }

}