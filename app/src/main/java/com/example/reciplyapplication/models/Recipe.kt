package com.example.reciplyapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
data class Recipe (
//    @PrimaryKey(autoGenerate = false)
    var idMeal: String,
    var strArea: String,
    var strCategory: String,
    var strMeal: String
    )