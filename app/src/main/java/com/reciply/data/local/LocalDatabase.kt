package com.reciply.data.local

import androidx.lifecycle.LiveData
import com.reciply.data.models.Meal
import com.reciply.data.models.User

interface LocalDatabase {
    // user
    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserByEmail(userEmail: String): LiveData<User?>

    // fav list


}