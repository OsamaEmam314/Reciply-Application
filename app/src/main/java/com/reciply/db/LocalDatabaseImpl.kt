package com.reciply.db

import android.content.Context
import com.reciply.models.User

class LocalDatabaseImpl(val context: Context): LocalDatabase {

    private var dao: UsersDao
    init {
        val db = UsersDatabase.getInstance(context)
        dao = db.getDao()
    }
    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }

    override suspend fun insertRecipeToFav(recipeId: String) {
        dao.insertRecipeToFav(recipeId)
    }

    override suspend fun deleteRecipeFromFav(recipeId: String) {
        dao.deleteRecipeFromFav(recipeId)
    }
}