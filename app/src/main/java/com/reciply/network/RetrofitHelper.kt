package com.reciply.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    val gson= GsonBuilder().serializeNulls().create()
    val retrofit= Retrofit.Builder().baseUrl("www.themealdb.com/api/json/v1/1/").addConverterFactory(
        GsonConverterFactory.create(gson)).build()

}