package com.reciply.repo

import androidx.lifecycle.LiveData
import com.reciply.data.local.LocalDatabase
import com.reciply.data.models.User

class UserRepositoryImpl(var localDatabase : LocalDatabase) : UserRepository {
    override suspend fun insertUser(user: User)
     {
        localDatabase.insertUser(user)
     }
    override suspend fun deleteUser(user: User)
    {
        localDatabase.deleteUser(user)
    }
   override  suspend fun getUserByEmail(userEmail: String): LiveData<User?>
     {
       return localDatabase.getUserByEmail(userEmail)
     }
}