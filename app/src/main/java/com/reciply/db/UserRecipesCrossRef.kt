package com.reciply.db

import androidx.room.Entity

@Entity(primaryKeys = ["idUser", "idMeal"])
data class UserRecipesCrossRef (
    var idUser: Int,
    var idMeal: String
)