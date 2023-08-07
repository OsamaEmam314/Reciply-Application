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
    fun getUserByEmail(userEmail: String): User?

}