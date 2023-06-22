package com.example.RestaurentApp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.restaurentApp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val intent = intent

        if (intent != null && intent.hasExtra("userId")) {
            val value = intent.getStringExtra("userId")
            Log.d("userId", value.toString())
            val vm= ViewModelProvider(this).get(MyModel::class.java)
            vm.userId = value!!.toInt()
        }

    }

}
