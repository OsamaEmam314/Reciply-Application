package com.example.githubtry

import android.content.Context

class LocalDatabaseImpl(val context: Context) : LocalDatabase{

    private var dao : UsersDao

    init {
        val db = UsersDatabase.getInstance(context)
        dao = db.getDao()
    }

    override suspend fun insertRecipeToFav(recipe: Recipe) {
        dao.insertRecipeToFav(recipe)
    }

    override suspend fun deleteRecipeFromFav(recipe: Recipe) {
        dao.deleteRecipeFromFav(recipe)
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user)
    }


}