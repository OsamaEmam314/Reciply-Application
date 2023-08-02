package com.reciply.db

import androidx.room.Delete
import androidx.room.Insert
import com.reciply.models.User

interface LocalDatabase {
    // user
    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)


    // fav list
    suspend fun insertRecipeToFav(recipeId: String)

    suspend fun deleteRecipeFromFav(recipeId: String)
}