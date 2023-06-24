package com.example.RestaurentApp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.RestaurentApp.Dao.OrderItemDao
import com.example.RestaurentApp.entity.OrderItem

@Database(entities = [OrderItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getOrderItemDo(): OrderItemDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context,AppDatabase::class.java,
                        "OrderItem").allowMainThreadQueries().build() }

            return INSTANCE
    }
}}