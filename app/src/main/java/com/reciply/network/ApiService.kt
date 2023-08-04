package com.reciply.network

import com.reciply.data.Meal
import com.reciply.data.MealResponse
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
//    @GET("api/json/v1/1/search.php")
//    suspend fun searchMealByName(@Query("s") mealName: String): MealResponse
    @GET("search.php")
    suspend fun searchMealByName(@Query("s") mealName: String): MealResponse

    @GET("random.php")
    suspend fun getRandomMeal():MealResponse



    @GET("search.php?f={randomLetter}")
    suspend fun listMealsByLetter(@Path(value="randomLetter",encoded = true) randomLetter:Char):MealResponse

}