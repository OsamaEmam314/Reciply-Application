package com.reciply.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["username"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = false)
    var idUser: Int = 1,

    var username: String,
    var password: String,
    var email: String
)
