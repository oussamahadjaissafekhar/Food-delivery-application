package com.example.RestaurentApp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.RestaurentApp.entity.OrderItem
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface OrderItemDao {
    @Insert()
    fun addMenuItemToOrder(orderItem: OrderItem)

    @Delete
    fun removeMenuItemFromOrder(orderItem: OrderItem)
    @Query("SELECT * FROM OrderItem WHERE order_item_id=:id")
    fun getOrderItemByid(id: Int): List<OrderItem>
    @Query("SELECT * FROM OrderItem WHERE menu_item_id=:id AND restaurant_id=:restid")
    fun getOrderItemBymenu(id: Int,restid:Int): List<OrderItem>

    @Query("SELECT * FROM OrderItem ")
    fun getOrderItems(): List<OrderItem>
    @Update
    fun updateOrderItems(user: List<OrderItem>)


}
