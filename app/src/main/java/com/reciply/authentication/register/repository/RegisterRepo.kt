package com.reciply.authentication.register.repository

import androidx.lifecycle.LiveData
import com.reciply.data.models.User

interface RegisterRepo {

    suspend fun insertUser(user: User)

    suspend fun getUserByEmail(userEmail: String): User?
}