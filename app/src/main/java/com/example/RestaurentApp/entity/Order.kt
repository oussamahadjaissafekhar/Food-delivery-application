package com.example.RestaurentApp.entity

data class Order(
    val user_id: Int,
    val restaurant_id: Int,
    val delivery_address: String,
    val additional_notes: String,
    val total_price: Float,
    val status: String
)
