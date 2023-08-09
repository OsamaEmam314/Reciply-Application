package com.reciply.search.repo

import com.reciply.data.models.Meal
import com.reciply.data.models.MealResponse
import com.reciply.data.models.Recipe
import com.reciply.data.models.UserFavList
import com.reciply.db.UserWithFavRecipes


interface SearchRepo {
    suspend fun getRemoteMealByName(mealName: String): MealResponse
    suspend fun getRemoteRandomMeal(): MealResponse
    suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean


    suspend fun insertIntoFavRecipe(userFavList: UserFavList)
    suspend fun insertRecipe(recipe: Meal)
    suspend fun deleteRecipe(recipe: Meal)
    suspend fun deleteFromFavRecipes(userFavList: UserFavList)

    suspend fun checkRecipeExists(mealID: String): Boolean

}