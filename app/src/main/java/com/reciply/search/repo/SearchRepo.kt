package com.reciply.search.repo

import com.reciply.data.data.models.MealResponse

interface SearchRepo {

    suspend fun getUserFavList(userID: Int) : List<String>
    suspend fun insertIntoFavRecipe(userID: Int, mealID: String)
    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)

    suspend fun getRemoteMealByName(mealName: String): MealResponse
    suspend fun getRemoteRandomMeal(): MealResponse

}