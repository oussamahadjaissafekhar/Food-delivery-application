package com.example.RestaurentApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
        binding.imageView2.setImageResource(vm.data.get(vm.position).listMenu?.get(vm.positionMenu)?.image!!)
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

    }
}