package com.reciply.repo

import com.reciply.data.MealResponse
import com.reciply.network.RemoteDataSource

class MealsRepositoryImpl(var remoteDataSource: RemoteDataSource):MealsRepository {
    override suspend fun getRemoteMealByName(mealName: String): MealResponse {
        return remoteDataSource.getMealByName(mealName)
    }

    override suspend fun getRemoteRandomMeal(): MealResponse {
        return remoteDataSource.getRandomMeal()
    }
}