package com.example.RestaurentApp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.RestaurentApp.MainActivity
import com.example.RestaurentApp.entity.Order
import com.example.RestaurentApp.entity.OrderItem
import com.example.RestaurentApp.entity.OrderRequest
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.retrofit.Endpoint
import com.example.restaurentApp.databinding.FragmentMenuBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuFragment : Fragment() {
    lateinit var binding: FragmentMenuBinding
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        val imageView: ImageView = binding.imageView2
        Glide.with(requireActivity()).load(vm.menuData.get(vm.positionMenu).image).into(imageView)
        binding.menuName.text = vm.menuData.get(vm.positionMenu).name
        binding.description.text = vm.menuData.get(vm.positionMenu).description
        binding.menuId.setText(vm.menuData.get(vm.positionMenu).idMenu.toString())
        binding.mainus.setOnClickListener(){
            var quantity = Integer.parseInt(binding.quantity.text.toString())
            quantity --
            if(quantity >= 1){
                binding.quantity.text = quantity.toString()
            }
        }
        binding.plus.setOnClickListener(){
            var quantity = Integer.parseInt(binding.quantity.text.toString())
            quantity ++
            if(quantity >= 1){
                binding.quantity.text = quantity.toString()
            }
        }
        binding.addToCard.setOnClickListener(){
            val menuId = binding.menuId.text.toString()
            val quantty = Integer.parseInt(binding.quantity.text.toString())
            //Log.d("added to card","menu : "+menuId+" quantity : "+quantty)
            val order = Order(1,2,"address","note2",250.00f,"en cours de preparation")
            val orderItems = mutableListOf<OrderItem>(
                OrderItem(1,3),OrderItem(2,1)
            )
            val gson = Gson()
            val orderString = gson.toJson(order)
            val orderItemsString = gson.toJson(orderItems)
            val orderRequest = OrderRequest(orderString,orderItemsString)
            addOrder(orderRequest)
            if(vm.userId == -1){
                Toast.makeText(requireActivity(), "please sign in first", Toast.LENGTH_SHORT).show()

                /*  redirect the user to sign in activity */
            }else{
                /* Add the order to the card*/
            }
        }

    }

    private fun addOrder(orderRequest: OrderRequest) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                Toast.makeText(this.requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = Endpoint.createEndpoint().createOrder(orderRequest)
            withContext(Dispatchers.Main) {
                Toast.makeText(requireActivity(), response.body().toString(), Toast.LENGTH_SHORT).show()
                Log.d("creat order response", response.body().toString())
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(requireActivity(), "result", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}