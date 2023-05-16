package com.example.RestaurentApp.entity

import com.example.RestaurentApp.entity.Menu

data class Restaurent(
    val restaurentId:Int,
    val restaurentName:String,
    val restaurentLogo:Int,
    val restaurentType: String,
    val rating:Float,
    val restaurentPhone:String,
    val restaurentLongitude:String,
    val restaurentLatitude:String,
    val restaurentInstagrame:String,
    val restaurentFacebook:String,
    val restaurentImage:Int,
    val listMenu:List<Menu>?
    ):java.io.Serializable
