package com.reciply.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reciply.data.data.models.User
import com.reciply.data.data.models.UserFavList

@Database(entities = [User::class, UserFavList::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun getDao(): UsersDao

    companion object{
        @Volatile
        private var INSTANCE: UsersDatabase? = null
        fun getInstance(context: Context): UsersDatabase{
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UsersDatabase::class.java,
                    "Users_Database",
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        INSTANCE = it
                    }
            }
        }
    }

}