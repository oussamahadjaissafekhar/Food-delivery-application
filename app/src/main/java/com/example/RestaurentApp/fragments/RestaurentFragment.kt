package com.example.RestaurentApp.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.RestaurentApp.adapters.MenuAdapter
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.retrofit.Endpoint
import com.example.restaurentApp.databinding.RestaurentfragmentBinding
import kotlinx.coroutines.*


class RestaurentFragment : Fragment() {
    lateinit var binding: RestaurentfragmentBinding
    lateinit var restaurentModel: MyModel
    lateinit var recyclerView: RecyclerView
    lateinit var vm: MyModel
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
        vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView = binding.RecyclerView
        restaurentModel = ViewModelProvider(requireActivity()).get(MyModel::class.java)
        val imageView: ImageView = binding.imageView4
        Glide.with(requireActivity()).load(vm.data[vm.position].restaurentImage).into(imageView)
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        loadMenu(restaurentModel.restaurantId)
    }

    fun loadMenu(restaurentId : Int) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = Endpoint.createEndpoint().getMenu(restaurentId)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    restaurentModel.menuData = response.body()!!.toMutableList()
                    recyclerView.adapter = MenuAdapter(requireActivity(),restaurentModel.menuData,vm)
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}