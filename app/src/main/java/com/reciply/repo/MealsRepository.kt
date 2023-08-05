package com.reciply.repo

import androidx.lifecycle.LiveData
import com.reciply.data.data.models.Meal
import com.reciply.data.data.models.MealResponse

interface MealsRepository {
    suspend fun getRemoteMealByName(mealName: String): MealResponse
    suspend fun getRemoteRandomMeal(): MealResponse

//    suspend fun insertRecipeToFav(userID: Int, recipeId: String)
//    suspend fun deleteRecipeFromFav(userId: Int, recipeId: String)
//    suspend fun getUserFavList(userID: Int) : LiveData<List<Meal>>


}