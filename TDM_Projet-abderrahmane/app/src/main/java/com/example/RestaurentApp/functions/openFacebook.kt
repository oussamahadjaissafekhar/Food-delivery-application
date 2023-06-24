package com.example.RestaurentApp

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openFacebook(ctx: Context, face:String){
    val data = Uri.parse(face)
    val intent = Intent(Intent.ACTION_VIEW, data)
    ctx.startActivity(intent)
}