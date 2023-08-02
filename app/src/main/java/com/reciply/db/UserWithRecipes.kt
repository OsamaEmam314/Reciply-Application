package com.reciply.db

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.reciply.models.Recipe
import com.reciply.models.User

data class UserWithRecipes(
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "idUser",
        entityColumn = "idMeal",
        associateBy = Junction(UserRecipesCrossRef::class)
    )

    val recipes: List<Recipe>

)
