package com.reciply.data.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["idUser", "idMeal"])
data class UserFavList (
    var idUser: Int,
    var idMeal: String
)