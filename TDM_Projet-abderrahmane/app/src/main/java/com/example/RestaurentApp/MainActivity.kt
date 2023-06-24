package com.example.RestaurentApp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.R
import com.example.movieexample.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById( R.id.navHost) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)

        val intent = intent
        val menu: Menu = binding.navView.menu
        val menuItemSignin: MenuItem? = menu.findItem(R.id.signIn2)

        val headerView = binding.navView.getHeaderView(0)

        val logout = headerView.findViewById<ImageView>(R.id.logout)
        logout.isVisible=false

        logout.setOnClickListener {
            menuItemSignin?.isVisible = true
            logout.isVisible=false
        }

        if (intent != null && intent.hasExtra("userId")) {
            val value = intent.getStringExtra("userId")
            Log.d("userId", value.toString())
            val vm = ViewModelProvider(this).get(MyModel::class.java)
            vm.userId = value!!.toInt()
            menuItemSignin?.isVisible = false
                   logout.isVisible=true

        }
    }

    override fun onSupportNavigateUp() = super.onSupportNavigateUp() || NavigationUI.navigateUp(navController,binding.drawerLayout)
}