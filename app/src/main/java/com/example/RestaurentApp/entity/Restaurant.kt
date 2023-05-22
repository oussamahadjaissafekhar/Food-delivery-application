package com.example.RestaurentApp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Restaurant")
data class Restaurant(
    @PrimaryKey val restaurant_id: Int,
    val name: String,
    val logo: Int,
    val location: String,
    val cuisine_type: String,
    val average_rating: Double,
    val contact_phone: String,
    val contact_email: String,
    val social_media_links: String
)