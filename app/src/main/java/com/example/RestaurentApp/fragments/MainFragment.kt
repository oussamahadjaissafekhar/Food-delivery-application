package com.example.RestaurentApp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.RestaurentApp.adapters.MyAdapter
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.R
import com.example.movieexample.databinding.MainfragmentBinding
import com.example.movieexample.retrofit.Endpoint
import kotlinx.coroutines.*


class BlankFragment : Fragment() {
    lateinit var binding: MainfragmentBinding
    lateinit var restaurentModel: MyModel
    lateinit var progressBar: ProgressBar
    lateinit var recyclerView: RecyclerView
    lateinit var vm: MyModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainfragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView = binding.recyclerView
        restaurentModel = ViewModelProvider(requireActivity()).get(MyModel::class.java)
        val button = view.findViewById(R.id.button) as Button
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar

        button.setOnClickListener {
            if(restaurentModel.data.isEmpty()) {
                loadRestaurents()
            }
            else {
                recyclerView.adapter = MyAdapter(requireActivity(),vm.data,vm)
            }
        }
    }

    fun loadRestaurents() {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        this.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = Endpoint.createEndpoint().getAllRestaurents()
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful && response.body() != null) {
                    Log.d("getRestaurent","succed and not null")
                    restaurentModel.data = response.body()!!.toMutableList()
                    recyclerView.adapter = MyAdapter(requireActivity(), restaurentModel.data,vm)
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}