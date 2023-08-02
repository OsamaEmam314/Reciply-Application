package com.reciply.network

import com.reciply.data.Meal
import com.reciply.data.MealResponse

object ApiClient {
    suspend fun getMealById(MealID: String):Meal{
        return RetrofitHelper.retrofit.create(ApiService::class.java).getMealById(MealID)
    }
}