package com.example.RestaurentApp.entity

import MenuItem
import Order
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "OrderItem",
    foreignKeys = [
        ForeignKey(entity = Order::class, parentColumns = ["order_id"], childColumns = ["order_id"], onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = MenuItem::class, parentColumns = ["menu_item_id"], childColumns = ["menu_item_id"], onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE)

    ]
)
data class OrderItem(
    @PrimaryKey val order_item_id: Int,
    val order_id: Int,
    val menu_item_id: Int,
    val quantity: Int
)
