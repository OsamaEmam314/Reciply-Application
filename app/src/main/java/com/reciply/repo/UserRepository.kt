package com.reciply.repo

import androidx.lifecycle.LiveData
import com.reciply.data.models.User

interface UserRepository {
    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserByEmail(userEmail: String): LiveData<User?>
}