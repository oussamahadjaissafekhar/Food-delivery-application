package com.example.RestaurentApp.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "OrderItem",

)
data class OrderItem(
    @PrimaryKey(autoGenerate = true)
    val order_item_id: Int,
    val order_id:Int,
    val menu_item_id: Int,
    var quantity: Int,
    val restaurant_id: Int,
    val image:String,
    val name: String,
    val description: String,
    val price: Double,
    val note: String
)
{
    @Ignore
    constructor (menu_item_id: Int,quantity: Int,restaurant_id: Int,image:String,name: String,description: String,price: Double, note: String) : this(0,0, menu_item_id, quantity,restaurant_id,image,name,description,price,note)
}
