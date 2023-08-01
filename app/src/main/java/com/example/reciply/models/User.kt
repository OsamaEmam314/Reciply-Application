package com.example.reciply.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = true)
    var idUser: Int = 1,
    val username: String,
    var password: String,
    val email:String)

{
    //var favRecipes = mutableListOf<Recipe>()
}
