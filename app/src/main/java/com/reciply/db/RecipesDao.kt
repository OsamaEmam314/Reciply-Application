package com.reciply.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.UserFavList

@Dao
interface RecipesDao {

//    @Query("SELECT idMeal FROM UserFavList WHERE idUser = :userID" )
//    suspend fun getUserFavList(userID: Int) : List<String>

//    @Query("INSERT INTO UserFavList VALUES(:userID, :mealID)" )
//    suspend fun insertIntoFavRecipe(userID: Int, mealID: String)
//
//    @Query("DELETE FROM UserFavList WHERE idUser = :userID AND idMeal = :mealID")
//    suspend fun deleteFromFavRecipe(userID: Int, mealID: String)

    @Query("SELECT EXISTS(SELECT * FROM UserFavList WHERE idUser = :userID AND idMeal = :mealID) ")
    suspend fun checkFavRecipe(userID: Int, mealID: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntoFavRecipe(userFavList: UserFavList)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: Meal)
    @Delete
    suspend fun deleteRecipe(recipe: Meal)
    @Delete
    suspend fun deleteFromFavRecipes(userFavList: UserFavList)

    @Query("SELECT * FROM Meal WHERE idMeal = :mealId")
    suspend fun getUsersWithRecipe(mealId: String): List<RecipeWithUsers>

    @Query("SELECT * FROM User WHERE idUser = :userId")
    suspend fun getRecipesWithUser(userId: Int): UserWithFavRecipes

    @Query("SELECT EXISTS(SELECT * FROM Meal WHERE idMeal = :mealID) ")
    suspend fun checkRecipeExists(mealID: String): Boolean

}