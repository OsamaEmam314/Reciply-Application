package com.reciply.data.data.local

import androidx.lifecycle.LiveData
import com.reciply.data.data.models.Meal
import com.reciply.data.data.models.User

interface LocalDatabase {
    // user
//    suspend fun insertUser(user: User)
//
//    suspend fun deleteUser(user: User)


    // fav list
    suspend fun insertRecipeToFav(userId: Int, recipeId: String)

    suspend fun deleteRecipeFromFav(userId: Int, recipeId: String)

    suspend fun getUserFavList(userID: Int) : LiveData<List<Meal>>

}