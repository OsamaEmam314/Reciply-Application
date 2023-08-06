package com.reciply.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reciply.data.models.Meal
import com.reciply.data.models.User

@Dao
interface UsersDao {
    // user
    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
    @Query("SELECT * FROM User WHERE email = :userEmail")
    suspend fun getUserByEmail(userEmail: String): LiveData<User?>

    // fav list
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeToFav(userID: Int, recipeId: String)

    @Delete
    suspend fun deleteRecipeFromFav(userId: Int, recipeId: String)

    @Query("SELECT idMeal FROM UserFavList WHERE idUser = :userID" )
    suspend fun getUserFavList(userID: Int) : LiveData<List<Meal>>
}