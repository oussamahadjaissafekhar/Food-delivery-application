package com.example.RestaurentApp.entity

data class user(
    val userId : Int,
    val password : String,
    val userName : String,
    val email : String,
    val phone : String,
    val address : String,
    val profilePicture : String
)