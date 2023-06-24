package com.example.RestaurentApp.entity

data class Rating(
    val user_id: String,
    val restaurent_id: String,
    val rating: String,
    val review: String,
)
