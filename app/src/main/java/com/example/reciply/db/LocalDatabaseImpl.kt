package com.example.reciply.db

import android.content.Context
import com.example.githubtry.LocalDatabase
import com.example.githubtry.UsersDao
import com.example.githubtry.UsersDatabase
import com.example.reciply.models.User

class LocalDatabaseImpl(val context: Context) : LocalDatabase {

    private var dao : UsersDao

    init {
        val db = UsersDatabase.getInstance(context)
        dao = db.getDao()
    }

    /*override suspend fun insertRecipeToFav(recipe: Recipe) {
        dao.insertRecipeToFav(recipe)
    }

    override suspend fun deleteRecipeFromFav(recipe: Recipe) {
        dao.deleteRecipeFromFav(recipe)
    }
*/
    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }


}