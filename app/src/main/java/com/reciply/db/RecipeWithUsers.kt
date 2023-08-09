package com.reciply.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.reciply.data.models.Meal
import com.reciply.data.models.Recipe
import com.reciply.data.models.User
import com.reciply.data.models.UserFavList

data class RecipeWithUsers(
    @Embedded val recipe : Meal,
    @Relation(
        parentColumn = "idMeal",
        entityColumn = "idUser",
        associateBy = Junction(UserFavList:: class)
    )
    var users : List<User>
)
