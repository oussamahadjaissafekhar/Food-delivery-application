package com.example.RestaurentApp.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.RestaurentApp.entity.OrderItem
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface OrderItemDao {
    @Insert()
    fun addMenuItemToOrder(orderItem: OrderItem)

    @Delete
    fun removeMenuItemFromOrder(orderItem: OrderItem)


    @Query("SELECT * FROM OrderItem ")
    fun getOrderItems(): List<OrderItem>


}
