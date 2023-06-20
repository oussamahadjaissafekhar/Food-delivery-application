package com.example.RestaurentApp.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "MenuItem",
    foreignKeys = [ForeignKey(
        entity = Restaurant::class,
        parentColumns = ["restaurant_id"],
        childColumns = ["restaurant_id"] ,
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE

    )]
)
data class MenuItem(
    @PrimaryKey val menu_item_id: Int,
    val restaurant_id: Int,
    val image:Int,
    val name: String,
    val description: String,
    val price: Double,
    val dietary_requirements: String
)
