package com.reciply.data.data.local

import androidx.lifecycle.LiveData
import com.reciply.data.models.User

interface LocalDatabase {
    // user
    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserByEmail(userEmail: String): User?
    // fav list
    suspend fun getUserFavList(userID: Int) : List<String>
    suspend fun insertIntoFavRecipe(userID: Int, mealID: String)
    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)

}