package com.reciply.search.repo

import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.data.models.MealResponse
import com.reciply.data.data.network.RemoteDataSource
import com.reciply.repo.MealsRepository

class SearchRepoImpl(var remoteDataSource: RemoteDataSource, var localDataSource: LocalDatabase): SearchRepo {
    override suspend fun getUserFavList(userID: Int): List<String> {
        return localDataSource.getUserFavList(userID)
    }

    override suspend fun insertIntoFavRecipe(userID: Int, mealID: String) {
        localDataSource.insertIntoFavRecipe(userID, mealID)
    }

    override suspend fun deleteFromFavRecipe(userID: Int, mealID: String) {
        localDataSource.insertIntoFavRecipe(userID, mealID)
    }

    override suspend fun getRemoteMealByName(mealName: String): MealResponse {
        return remoteDataSource.getMealByName(mealName)
    }

    override suspend fun getRemoteRandomMeal(): MealResponse {
        return remoteDataSource.getRandomMeal()
    }
}