package com.reciply.fav.repo

import com.reciply.data.models.Meal

interface FavRepo {

    suspend fun getUserFavList(userID: Int) : List<String>
    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)

    suspend fun getMealById(mealId: String): Meal

}