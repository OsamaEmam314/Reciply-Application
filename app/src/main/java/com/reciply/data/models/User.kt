package com.reciply.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var idUser: Int? = null,
    var username: String,
    var password: String,
    var email: String
)

