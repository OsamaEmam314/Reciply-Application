package com.reciply.data.data.network

import com.reciply.data.data.models.MealResponse

object ApiClient: RemoteDataSource {
    override suspend fun getMealByName(mealName: String): MealResponse {
        return RetrofitHelper.retrofit.create(ApiService::class.java).searchMealByName(mealName)
    }

    override suspend fun getRandomMeal(): MealResponse {
        return RetrofitHelper.retrofit.create(ApiService::class.java).getRandomMeal()
        }
}