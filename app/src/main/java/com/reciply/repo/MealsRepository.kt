package com.reciply.repo

import com.reciply.data.data.models.MealResponse

interface MealsRepository {
    suspend fun getRemoteMealByName(mealName: String): MealResponse
    suspend fun getRemoteRandomMeal(): MealResponse
    suspend fun getRemoteMealsList(randLetter:String): MealResponse


}