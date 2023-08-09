package com.reciply.fav.repo

import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.UserFavList
import com.reciply.db.UserWithFavRecipes

interface FavRepo {

//    suspend fun getUserFavList(userID: Int) : List<String>
//    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)

    suspend fun getMealById(mealId: String): Meal

    suspend fun getRecipesWithUser(userId: Int): UserWithFavRecipes
    suspend fun insertIntoFavRecipe(userFavList: UserFavList)
    suspend fun insertRecipe(recipe: Meal)
    suspend fun deleteRecipe(recipe: Meal)
    suspend fun deleteFromFavRecipes(userFavList: UserFavList)

}