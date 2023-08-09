package com.reciply.data.data.local

import androidx.lifecycle.LiveData
import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.User
import com.reciply.data.models.UserFavList
import com.reciply.db.UserWithFavRecipes

interface LocalDatabase {
    // user
    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun getUserByEmail(userEmail: String): User?
    // fav list
//    suspend fun getUserFavList(userID: Int) : List<String>
//    suspend fun insertIntoFavRecipe(userID: Int, mealID: String)
//    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)
    suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean

    suspend fun getRecipesWithUser(userId: Int): UserWithFavRecipes
    suspend fun insertIntoFavRecipe(userFavList: UserFavList)
    suspend fun insertRecipe(recipe: Meal)
    suspend fun deleteRecipe(recipe: Meal)
    suspend fun deleteFromFavRecipes(userFavList: UserFavList)
    suspend fun checkRecipeExists(mealID: String): Boolean


}