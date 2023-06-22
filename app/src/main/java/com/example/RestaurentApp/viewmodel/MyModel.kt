package com.example.RestaurentApp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RestaurentApp.entity.Menu
import com.example.RestaurentApp.entity.OrderItem
import com.example.RestaurentApp.entity.Restaurent
import com.example.movieexample.R

class MyModel:ViewModel (){
    private val _orderItems = MutableLiveData<List<OrderItem>>()
    val orderItems: LiveData<List<OrderItem>> = _orderItems

    fun updateOrderItems(updatedItems: List<OrderItem>) {
        _orderItems.value = updatedItems
    }
    var restaurantId = -1
    var menuId = -1
    var userId=-1
    var data = mutableListOf<Restaurent>()
    var menuData = mutableListOf<Menu>()
    val restaurents=loadData()
    var position = -1
    var positionMenu =-1
    var restaurentId_card=0

    fun loadData():List<Restaurent> {
        val listMenu = mutableListOf<Menu>(

        )
        val data = mutableListOf<Restaurent>()
        /*data.add(Restaurent(1,"res1",R.drawable.logo,"Indien", 4.5F,"0540589997","41.40338","2.17403","https://www.instagram.com","https://www.facebook.com","R.drawable.rest1,listMenu"))
        data.add(Restaurent(2,"res1",R.drawable.logo,"Indien", 4.5F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(3,"res1",R.drawable.logo2,"Indien", 4F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest2,listMenu))
        data.add(Restaurent(4,"res1",R.drawable.logo3,"Indien", 3F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(5,"res1",R.drawable.logo2,"Indien", 1F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(6,"res1",R.drawable.logo,"Indien", 4.5F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(7,"res1",R.drawable.logo,"Indien", 4.5F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest2,listMenu))
        data.add(Restaurent(8,"res1",R.drawable.logo2,"Indien", 4F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(9,"res1",R.drawable.logo3,"Indien", 3F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(10,"res1",R.drawable.logo2,"Indien", 1F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(11,"res1",R.drawable.logo,"Indien", 4.5F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(12,"res1",R.drawable.logo,"Indien", 4.5F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest1,listMenu))
        data.add(Restaurent(13,"res1",R.drawable.logo2,"Indien", 4F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest2,listMenu))
        data.add(Restaurent(14,"res1",R.drawable.logo3,"Indien", 3F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest2,listMenu))
        data.add(Restaurent(15,"res1",R.drawable.logo2,"Indien", 1F,"0540589997","7ai 3li sadeg","41.40338","2.17403","",R.drawable.rest2,listMenu))*/
        return data
    }
}