package com.example.RestaurentApp.entity


data class OrderItem1(
 val order_item_id: Int,
    val menu_item_id: Int,
    val quantity: Int,
    val restaurant_id: Int,
    val image:Int,
    val name: String,
    val description: String,
    val price: Double,
    val dietary_requirements: String
)


