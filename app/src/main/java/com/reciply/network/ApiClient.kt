package com.reciply.network

import com.reciply.data.Meal
import com.reciply.data.MealResponse

object ApiClient {
    suspend fun getMealByName(MealName: String):MealResponse{
        return RetrofitHelper.retrofit.create(ApiService::class.java).searchMealByName(MealName)
    }
}