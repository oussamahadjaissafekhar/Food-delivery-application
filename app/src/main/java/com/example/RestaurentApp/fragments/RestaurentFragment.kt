package com.example.RestaurentApp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.RestaurentApp.adapters.MenuAdapter
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.databinding.RestaurentfragmentBinding


class RestaurentFragment : Fragment() {
    lateinit var binding: RestaurentfragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = RestaurentfragmentBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        //binding.imageView4.setImageResource(vm.data.get(vm.position).image)
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.RecyclerView.adapter = MenuAdapter(requireActivity(),vm.data[vm.position].listMenu!!,vm)
    }


}