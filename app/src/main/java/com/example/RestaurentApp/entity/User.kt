package com.example.RestaurentApp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey val user_id: Int,
    val name: String,
    val email: String,
    val phone_number: String,
    val address: String,
    val password: String,
    val profile_picture: String,
)
