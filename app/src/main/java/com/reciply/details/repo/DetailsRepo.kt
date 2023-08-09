package com.reciply.details.repo

import com.reciply.data.models.Meal
import com.reciply.data.models.UserFavList

interface DetailsRepo {

    suspend fun insertIntoFavRecipe(userFavList: UserFavList)
    suspend fun insertRecipe(recipe: Meal)
    suspend fun deleteRecipe(recipe: Meal)
    suspend fun deleteFromFavRecipes(userFavList: UserFavList)

    suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean

}