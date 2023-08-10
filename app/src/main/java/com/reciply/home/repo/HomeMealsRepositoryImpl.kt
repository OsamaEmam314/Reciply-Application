package com.reciply.home.repo

import com.reciply.data.models.MealResponse
import com.reciply.network.RemoteDataSource

class HomeMealsRepositoryImpl(private var remoteDataSource: RemoteDataSource): HomeMealsRepository {


    override suspend fun getRemoteRandomMeal(): MealResponse {
        return remoteDataSource.getRandomMeal()
    }

    override suspend fun getRemoteMealsList(randLetter:String): MealResponse {
        return remoteDataSource.listMealsByLetter(randLetter)
    }
}