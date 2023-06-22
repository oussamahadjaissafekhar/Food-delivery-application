package com.example.RestaurentApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RestaurentApp.AppDatabase
import com.example.RestaurentApp.adapters.CardAdapter
import com.example.RestaurentApp.entity.OrderItem
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.databinding.Fragment1Binding

class Fragment1 : Fragment() {
    lateinit var binding: Fragment1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment1Binding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)

        val data= AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()?.getOrderItems()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = CardAdapter(requireActivity(),
            data!! as MutableList<OrderItem>,vm)

        var sum :Double= 0.0

        for (i in 0 until data.size ) {
            val multiplication = data[i].price * data[i].quantity
            sum += multiplication
        }


        // Update the UI with the updated price
        binding.textView10.text=sum.toString()+" DA"
        val totalprice=sum+300
        binding.textView14.text=totalprice.toString()+" DA"

        vm.orderItems.observe(viewLifecycleOwner) { data ->
            // Assume that orderItems is a List<OrderItem> with the updated price


            var sum :Double= 0.0

            for (i in 0 until data.size ) {
                val multiplication = data[i].price * data[i].quantity
                sum += multiplication
            }

            // Update the UI with the updated price
            binding.textView10.text=sum.toString()+" DA"
            val totalprice=sum+300
            binding.textView14.text=totalprice.toString()+" DA"

        }

    }

}