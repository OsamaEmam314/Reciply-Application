package com.reciply.home.repo

import com.reciply.data.models.MealResponse


interface HomeMealsRepository {
    suspend fun getRemoteRandomMeal(): MealResponse
    suspend fun getRemoteMealsList(randLetter:String): MealResponse


}