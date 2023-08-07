package com.reciply.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface RecipesDao {

    @Query("SELECT idMeal FROM UserFavList WHERE idUser = :userID" )
    suspend fun getUserFavList(userID: Int) : List<String>

    @Query("INSERT INTO UserFavList VALUES(:userID, :mealID)" )
    suspend fun insertIntoFavRecipe(userID: Int, mealID: String)

    @Query("DELETE FROM UserFavList WHERE idUser = :userID AND idMeal = :mealID")
    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)

//    @Query("SELECT EXISTS(SELECT * FROM UserFavList WHERE idUser = :userID AND idMeal = :mealID) ")
//    suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean

}