package com.example.RestaurentApp

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openInsta(ctx: Context, Insta:String){
    val data = Uri.parse(Insta)
    val intent = Intent(Intent.ACTION_VIEW, data)
    ctx.startActivity(intent)
}