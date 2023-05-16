package com.example.RestaurentApp.entity

import com.example.RestaurentApp.entity.Menu

data class Restaurent(
    val restaurentId:Int,
    val restaurentName:String,
    val restaurentLogo:String,
    val restaurentType: String,
    val rating:Float,
    val restaurentPhone:String,
    val restaurentLongitude:String,
    val restaurentLatitude:String,
    val restaurentInstagrame:String,
    val restaurentFacebook:String,
    val restaurentImage:String,
    val listMenu: MutableList<Menu>?
    ):java.io.Serializable
