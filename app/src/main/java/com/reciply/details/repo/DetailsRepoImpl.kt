package com.reciply.details.repo

import com.reciply.data.data.local.LocalDatabase
import com.reciply.data.models.Meal
import com.reciply.data.models.UserFavList
import com.reciply.data.network.RemoteDataSource
import com.reciply.search.repo.SearchRepo

class DetailsRepoImpl(var remoteDataSource: RemoteDataSource, var localDataSource: LocalDatabase): DetailsRepo {
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

    override suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean {
       return localDataSource.checkFavRecipe(userID, mealID)
    }


}