package com.reciply.network

import com.reciply.data.models.MealResponse
import com.reciply.data.network.RetrofitHelper


object ApiClient:RemoteDataSource {
    override suspend fun getMealByName(mealName: String): MealResponse {
        return RetrofitHelper.retrofit.create(ApiService::class.java).searchMealByName(mealName)
    }

    override suspend fun getRandomMeal(): MealResponse {
        return RetrofitHelper.retrofit.create(ApiService::class.java).getRandomMeal()
        }

    override suspend fun listMealsByLetter(randLetter:String): MealResponse {
        return RetrofitHelper.retrofit.create(ApiService::class.java).listMealsByLetter(randLetter)
    }
}