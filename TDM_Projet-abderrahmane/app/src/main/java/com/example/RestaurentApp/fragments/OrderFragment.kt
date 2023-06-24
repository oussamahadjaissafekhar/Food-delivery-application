package com.example.RestaurentApp.fragments

import Order
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RestaurentApp.AppDatabase
import com.example.RestaurentApp.adapters.CardAdapter
import com.example.RestaurentApp.entity.OrderItem
import com.example.RestaurentApp.entity.OrderItem1
import com.example.RestaurentApp.entity.OrderRequest
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.R
import com.example.movieexample.databinding.Fragment1Binding
import com.example.movieexample.databinding.FragmentOrderBinding
import com.example.movieexample.retrofit.Endpoint
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderFragment : Fragment() {
    lateinit var binding: FragmentOrderBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        return binding.root


}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fun addOrder(orderRequest: OrderRequest) {
            val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                requireActivity().runOnUiThread {
                    Toast.makeText(this.requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
                }

            }
            CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
                val response = Endpoint.createEndpoint().createOrder(orderRequest)
                withContext(Dispatchers.Main) {
                    Log.d("creat order response", response.body().toString())
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(requireActivity(), "the order has benn placed succesfully", Toast.LENGTH_SHORT).show()
                        AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()?.deleteAll()
                    } else {
                        Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.placeorder.setOnClickListener() {
            val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
            val address=binding.address.text
            val notes=binding.notes.text
            val data= AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()?.getOrderItems()!!.toMutableList()

            val order=Order(vm.userId,data[0].restaurant_id,address.toString(),notes.toString(),vm.totalPrice.toDouble(),"active")
            val orderItems: List<OrderItem1> = data.map { orderItem ->
                OrderItem1(orderItem.menu_item_id,orderItem.quantity)
            }
            val gson = Gson()
            val orderString = gson.toJson(order)
            val orderItemsString = gson.toJson(orderItems)
            val orderRequest = OrderRequest(orderString,orderItemsString)

            addOrder(orderRequest)
        }



    }
}