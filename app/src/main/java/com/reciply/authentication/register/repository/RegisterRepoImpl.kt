package com.reciply.authentication.register.repository

import androidx.lifecycle.LiveData
import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RegisterRepoImpl(var localDatabase: LocalDatabase) : RegisterRepo {

    override suspend fun getUserByEmail(userEmail: String): User? {
        return localDatabase.getUserByEmail(userEmail)
    }

    override suspend fun insertUser(user: User) {
        localDatabase.insertUser(user)
    }
}
