package com.example.RestaurentApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.restaurentApp.databinding.FragmentMenuBinding

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
            if(vm.userId == -1){
                Toast.makeText(requireActivity(), "please sign in first", Toast.LENGTH_SHORT).show()
                /*  redirect the user to sign in activity */
            }else{
                /* Add the order to the card*/
            }
        }

    }
}