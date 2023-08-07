package com.reciply.authentication.login.repository

import com.reciply.authentication.register.repository.RegisterRepo
import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.models.User

class LoginRepoImpl(var localDatabase: LocalDatabase) : LoginRepo {
    override suspend fun getUserByEmail(userEmail: String): User? {
        return localDatabase.getUserByEmail(userEmail)
    }
}