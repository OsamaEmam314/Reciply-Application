package com.reciply.authentication.login.repository

import com.reciply.data.models.User

interface LoginRepo {
    suspend fun getUserByEmail(userEmail: String): User?

}