package com.reciply.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = false)
    var idMeal: String,
    var strMeal: String,
    var strArea: String,
    var strCategory: String,
    val strInstructions: String,
    val strYoutube: String,
    val strMealThumb: String
)
