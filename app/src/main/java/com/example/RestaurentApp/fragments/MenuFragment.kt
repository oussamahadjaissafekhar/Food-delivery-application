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
        /*val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        val imageView: ImageView = binding.imageView2
        Glide.with(requireActivity()).load(vm.menuData.get(vm.positionMenu).image).into(imageView)
        binding.menuName.text = vm.menuData.get(vm.positionMenu).name
        binding.description.text = vm.menuData.get(vm.positionMenu).description
        binding.menuId.setText(vm.menuData.get(vm.positionMenu).idMenu.toString())*/
        val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        Glide.with(view)
            .load(vm.menuData.get(vm.positionMenu).image)
            .into(binding.imageView2)
       // binding.imageView2.setImageResource(Integer.parseInt(vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.image))
        binding.textView4.text = vm.menuData.get(vm.positionMenu).name
        binding.textView5.text = vm.menuData.get(vm.positionMenu).description
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
        binding.button3.setOnClickListener() {
            val note = binding.note.text.toString()

            var item = OrderItem(
                vm.menuId,
                Integer.parseInt(binding.textView7.text.toString()),
                vm.restaurantId,
                vm.menuData.get(vm.positionMenu).image.toString(),
                vm.menuData.get(vm.positionMenu).name.toString(),
                vm.menuData.get(vm.positionMenu).description.toString(),
                vm.menuData.get(vm.positionMenu).price!!.toDouble(),
                note
            )
            //verify that the database is empty to set the restaurent id
            val cart_items =
                AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()?.getOrderItems()
                    ?.toMutableList()
            if (cart_items!!.size == 0) {
                AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()
                    ?.addMenuItemToOrder(item)
                val updatedItems =
                    AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()
                        ?.getOrderItems()!!
                        .toMutableList()

                vm.updateOrderItems(updatedItems)
                vm.restaurentId_card = item.restaurant_id


            } else {
                if(item.restaurant_id==vm.restaurentId_card){
                //verify if it is already exist in database
                var potential_item = AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()
                    ?.getOrderItemBymenu(item.menu_item_id, item.restaurant_id)!!
                    .toMutableList()
                if (potential_item.size > 0) {
                    Toast.makeText(
                        requireActivity(),
                        "this menu already added to your cart      you can modify it in your card ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()
                        ?.addMenuItemToOrder(item)
                    val updatedItems =
                        AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()
                            ?.getOrderItems()!!
                            .toMutableList()

                    vm.updateOrderItems(updatedItems)
                    val order_item = AppDatabase.buildDatabase(requireActivity())?.getOrderItemDo()
                        ?.getOrderItems()?.toMutableList()
                    binding.textView8.text = order_item?.get(0)?.name.toString()

                }
            }
              else{
                    Toast.makeText(requireActivity(), "you cannot add menus from different restaurents , please choose one ", Toast.LENGTH_SHORT).show()
              }
            }
        }
    }
}