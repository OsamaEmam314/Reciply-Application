package com.example.githubtry

import androidx.room.*
import com.example.reciply.models.User

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)


    // for fav list
   /* @Insert
    suspend fun insertRecipeToFav(recipe: Recipe)

    @Delete
    suspend fun deleteRecipeFromFav(recipe: Recipe)*/

}