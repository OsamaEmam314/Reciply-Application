package com.reciply.fav.repo

import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.models.Meal
import com.reciply.data.models.MealResponse
import com.reciply.data.models.Recipe
import com.reciply.data.models.UserFavList
import com.reciply.data.network.RemoteDataSource
import com.reciply.db.UserWithFavRecipes

class FavRepoImpl(var remoteDataSource: RemoteDataSource, var localDataSource: LocalDatabase): FavRepo {
//    override suspend fun getUserFavList(userID: Int): List<String> {
//        return localDataSource.getUserFavList(userID)
//    }

//    override suspend fun deleteFromFavRecipe(userID: Int, mealID: String) {
//        localDataSource.deleteFromFavRecipe(userID, mealID)
//    }

    override suspend fun getMealById(mealId: String): Meal {
        return remoteDataSource.getMealById(mealId)
    }

    override suspend fun getRecipesWithUser(userId: Int): UserWithFavRecipes {
        return localDataSource.getRecipesWithUser(userId)
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

}