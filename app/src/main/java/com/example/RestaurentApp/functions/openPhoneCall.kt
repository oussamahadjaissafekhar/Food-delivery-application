package com.example.RestaurentApp

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openCall(ctx: Context,phone:String){
    val data = Uri.parse("tel:$phone")
    val intent = Intent(Intent.ACTION_DIAL, data)
    ctx.startActivity(intent)
}