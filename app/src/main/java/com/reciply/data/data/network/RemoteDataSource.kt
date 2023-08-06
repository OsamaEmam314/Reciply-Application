package com.reciply.data.data.network

import com.reciply.data.data.models.MealResponse

interface RemoteDataSource {
    suspend fun getMealByName(mealName: String): MealResponse
    suspend fun getRandomMeal(): MealResponse
    suspend fun listMealsByLetter(randLetter:String): MealResponse
}