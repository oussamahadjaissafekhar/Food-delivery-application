package com.example.RestaurentApp.entity

data class Menu(
    val idMenu:Int,
    val name:String,
    val image:String,
    val price:Float,
    val description:String,
    val dietary_requirements:String
):java.io.Serializable
