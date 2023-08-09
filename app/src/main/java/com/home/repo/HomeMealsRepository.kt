package com.home.repo

import com.reciply.data.models.MealResponse


interface HomeMealsRepository {
    suspend fun getRemoteMealByName(mealName: String): MealResponse
    suspend fun getRemoteRandomMeal(): MealResponse
    suspend fun getRemoteMealsList(randLetter:String): MealResponse


}