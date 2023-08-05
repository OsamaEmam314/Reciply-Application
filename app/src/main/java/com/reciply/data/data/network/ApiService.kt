package com.reciply.data.data.network

import com.reciply.data.data.models.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    @GET("api/json/v1/1/search.php")
//    suspend fun searchMealByName(@Query("s") mealName: String): MealResponse
    @GET("search.php")
    suspend fun searchMealByName(@Query("s") mealName: String): MealResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse
}