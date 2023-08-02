package com.reciply.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    var idMeal: Int,
    var strMeal: String,
    var strArea: String,
    var strCategory: String
)
