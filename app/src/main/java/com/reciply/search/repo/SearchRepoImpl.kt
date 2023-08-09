package com.reciply.search.repo

import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.models.Meal
import com.reciply.data.models.MealResponse
import com.reciply.data.models.UserFavList
import com.reciply.data.network.RemoteDataSource

class SearchRepoImpl(var remoteDataSource: RemoteDataSource, var localDataSource: LocalDatabase): SearchRepo {
    override suspend fun getRemoteMealByName(mealName: String): MealResponse {
        return remoteDataSource.getMealByName(mealName)
    }

    override suspend fun getRemoteRandomMeal(): MealResponse {
        return remoteDataSource.getRandomMeal()
    }

    override suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean {
        return localDataSource.checkFavRecipe(userID, mealID)
    }

    override suspend fun insertIntoFavRecipe(userFavList: UserFavList) {
        localDataSource.insertIntoFavRecipe(userFavList)
    }

    override suspend fun insertRecipe(recipe: Meal) {
        localDataSource.insertRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: Meal) {
        localDataSource.deleteRecipe(recipe)
    }

    override suspend fun deleteFromFavRecipes(userFavList: UserFavList) {
        localDataSource.deleteFromFavRecipes(userFavList)
    }

    override suspend fun checkRecipeExists(mealID: String): Boolean {
        return localDataSource.checkRecipeExists(mealID)
    }
}