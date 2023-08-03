package com.reciply.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reciply.models.User
import kotlinx.coroutines.internal.synchronized

@Database(entities = [User::class], version = 1)
abstract class UsersDatabase: RoomDatabase() {

    abstract fun getDao(): UsersDao

    companion object{
        @Volatile
        private var INSTANCE: UsersDatabase? = null
        fun getInstance(context: Context): UsersDatabase{
            return INSTANCE ?: kotlin.synchronized(this) {
                Room.databaseBuilder(
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