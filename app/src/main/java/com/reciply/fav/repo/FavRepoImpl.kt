package com.reciply.fav.repo

import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.models.Meal
import com.reciply.data.models.MealResponse
import com.reciply.data.network.RemoteDataSource

class FavRepoImpl(var remoteDataSource: RemoteDataSource, var localDataSource: LocalDatabase): FavRepo {
    override suspend fun getUserFavList(userID: Int): List<String> {
        return localDataSource.getUserFavList(userID)
    }

    override suspend fun deleteFromFavRecipe(userID: Int, mealID: String) {
        localDataSource.deleteFromFavRecipe(userID, mealID)
    }

    override suspend fun getMealById(mealId: String): Meal {
        return remoteDataSource.getMealById(mealId)
    }

}