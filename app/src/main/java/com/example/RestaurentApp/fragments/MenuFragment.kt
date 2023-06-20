package com.example.RestaurentApp.fragments

import Order
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.RestaurentApp.AppDatabase
import com.example.RestaurentApp.entity.OrderItem
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.databinding.FragmentMenuBinding

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
        Glide.with(view)
            .load(vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.image)
            .into(binding.imageView2)
       // binding.imageView2.setImageResource(Integer.parseInt(vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.image))
        binding.textView4.text = vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.nom
        binding.textView5.text = vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.descriptif
        binding.button.setOnClickListener(){
            var quantity = Integer.parseInt(binding.textView7.text.toString())
            quantity --
            if(quantity >= 0){
                binding.textView7.text = quantity.toString()
            }
        }
        binding.button2.setOnClickListener(){
            var quantity = Integer.parseInt(binding.textView7.text.toString())
            quantity ++
            if(quantity >= 0){
                binding.textView7.text = quantity.toString()
            }
        }
        binding.button3.setOnClickListener(){


            var item=OrderItem(vm.menuId,Integer.parseInt(binding.textView7.text.toString()),vm.restaurantId,vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.image.toString(),vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.nom.toString(),vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.descriptif.toString(), vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.prix!!.toDouble(),"ss")
            AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()?.addMenuItemToOrder(item)

            val order_item=AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()?.getOrderItems()?.toMutableList()
            binding.textView8.text= order_item?.get(0)?.name.toString()

        }

    }
}