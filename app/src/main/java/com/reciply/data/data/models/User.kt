package com.reciply.data.data.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["username"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var idUser: Int?,
    var username: String,
    var password: String,
    var email: String
)
