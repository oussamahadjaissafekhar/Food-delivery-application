package com.example.RestaurentApp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.RestaurentApp.entity.Rating
import com.example.RestaurentApp.viewmodel.MyModel
import com.example.movieexample.databinding.FragmentRatingBinding
import com.example.movieexample.retrofit.Endpoint
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RatingFragment : Fragment() {
    lateinit var binding: FragmentRatingBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
          binding = FragmentRatingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button4.setOnClickListener(){
            val note=binding.editTextText.text.toString()
            val review=binding.editTextText2.text.toString()
            val vm= ViewModelProvider(requireActivity()).get(MyModel::class.java)
            val rating= Rating(vm.userId.toString(),vm.restaurantId.toString(),note,review.toString())
            val gson = Gson()
            val ratingString = gson.toJson(rating)
            creatRating(vm.userId.toString(),vm.restaurantId.toString(),note,review)
        }

    }
    private fun creatRating( userId: String,
                           restaurantId: String,
                         rating: String,
                           review: String) {
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            requireActivity().runOnUiThread {
                Toast.makeText(requireActivity(), throwable.message, Toast.LENGTH_SHORT).show()
            }

        }
        CoroutineScope(Dispatchers.IO+ exceptionHandler).launch {
            val response = Endpoint.createEndpoint().submitRating(userId,restaurantId,rating,review)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(requireActivity(), response.body().toString(), Toast.LENGTH_SHORT).show()
                    Log.d("creat order response", response.body().toString())
                    if (response.isSuccessful && response.body() != null) {
                        Toast.makeText(requireActivity(), "result", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireActivity(), "Une erreur s'est produite", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}