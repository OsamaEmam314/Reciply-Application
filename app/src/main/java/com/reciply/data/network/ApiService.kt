package com.reciply.network


import com.reciply.data.models.Meal
import com.reciply.data.models.MealResponse
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
    suspend fun getRandomMeal(): MealResponse



    @GET("search.php")
    suspend fun listMealsByLetter(@Query("f") randomLetter:String): MealResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") mealId: String): Meal

}