package com.reciply.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.reciply.models.Recipe
import com.reciply.models.User

@Dao
interface UsersDao {

    // user
    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


    // fav list
    @Insert
    suspend fun insertRecipeToFav(recipeId: String)

    @Delete
    suspend fun deleteRecipeFromFav(recipeId: String)


}