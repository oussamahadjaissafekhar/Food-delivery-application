package com.example.RestaurentApp.entity

data class Menu(
    val idMenu:Int,
    val nom:String,
    val image:String,
    val prix:Int,
    val descriptif:String
):java.io.Serializable
