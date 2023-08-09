package com.reciply.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.User
import com.reciply.data.models.UserFavList

data class UserWithFavRecipes (
    @Embedded val user : User,
    @Relation(
        parentColumn = "idUser",
        entityColumn = "idMeal",
        associateBy = Junction(UserFavList:: class)
    )
    var recipes : List<Meal>
)