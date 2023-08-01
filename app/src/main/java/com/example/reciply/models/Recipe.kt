package com.example.reciply.models

//@Entity
data class Recipe (
    //@PrimaryKey(autoGenerate = false)
    var idMeal: String,
    var strArea: String,
    var strCategory: String,
    var strMeal: String
    )