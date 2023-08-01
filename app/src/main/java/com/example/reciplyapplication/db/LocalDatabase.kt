package com.example.githubtry

import com.example.reciplyapplication.models.Recipe
import com.example.reciplyapplication.models.User

interface LocalDatabase {

    // fav list
    suspend fun insertRecipeToFav(recipe: Recipe)

    suspend fun deleteRecipeFromFav(recipe: Recipe)


    // user
    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)
}