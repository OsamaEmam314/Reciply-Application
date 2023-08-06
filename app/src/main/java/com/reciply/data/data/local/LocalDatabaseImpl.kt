package com.reciply.data.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.reciply.data.data.models.Meal
import com.reciply.db.UsersDatabase
import com.reciply.data.data.models.User
import com.reciply.db.RecipesDao
import com.reciply.db.UsersDao


class LocalDatabaseImpl(context: Context): LocalDatabase {

    private lateinit var daoUser: UsersDao
    private lateinit var daoRecipe: RecipesDao
    init {
        val db = UsersDatabase.getInstance(context)
        daoUser = db.getDao()
        daoRecipe = db.getRecipeDao()
    }

    override suspend fun getUserFavList(userID: Int): List<String> {
        return daoRecipe.getUserFavList(userID)
    }

    override suspend fun insertIntoFavRecipe(userID: Int, mealID: String) {
        daoRecipe.insertIntoFavRecipe(userID, mealID)
    }

    override suspend fun deleteFromFavRecipe(userID: Int, mealID: String) {
        daoRecipe.deleteFromFavRecipe(userID, mealID)
    }

//    override suspend fun insertUser(user: User) {
//        dao.insertUser(user)
//    }
//
//    override suspend fun deleteUser(user: User) {
//        dao.deleteUser(user)
//    }


}