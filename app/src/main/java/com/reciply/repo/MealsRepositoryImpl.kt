package com.reciply.repo

import com.reciply.data.models.MealResponse
import com.reciply.network.ApiClient
import com.reciply.network.RemoteDataSource

class MealsRepositoryImpl(var remoteDataSource: RemoteDataSource):MealsRepository {
    override suspend fun getRemoteMealByName(mealName: String): MealResponse {
        return remoteDataSource.getMealByName(mealName)
    }

    override suspend fun getRemoteRandomMeal(): MealResponse {
        return remoteDataSource.getRandomMeal()
    }

    override suspend fun getRemoteMealsList(randLetter:String): MealResponse {
        return remoteDataSource.listMealsByLetter(randLetter)
    }


    // fav list (local db)
//    override suspend fun insertRecipeToFav(userID: Int, recipeId: String) {
//        localDataSource.insertRecipeToFav(userID, recipeId)
//    }
//
//    override suspend fun deleteRecipeFromFav(userId: Int, recipeId: String) {
//        localDataSource.deleteRecipeFromFav(userId, recipeId)
//    }
//
//    override suspend fun getUserFavList(userID: Int): LiveData<List<Meal>> {
//        return localDataSource.getUserFavList(userID)
//    }
}