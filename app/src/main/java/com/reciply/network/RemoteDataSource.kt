package com.reciply.network

import com.reciply.data.MealResponse

interface RemoteDataSource {
    suspend fun getMealByName(mealName: String): MealResponse
    suspend fun getRandomMeal():MealResponse
}