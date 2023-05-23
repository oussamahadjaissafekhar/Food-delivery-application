package com.example.movieexample.retrofit

import com.example.RestaurentApp.entity.Menu
import com.example.RestaurentApp.entity.Restaurent
import com.example.RestaurentApp.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("restaurent/getall")
    suspend fun getAllRestaurents(): Response<List<Restaurent>>
    @GET("getMenu")
    suspend fun getMenu(@Query("restaurentId") restaurentId: Int): Response<List<Menu>>
    companion object {
        @Volatile
        var endpoint: Endpoint? = null
        fun createEndpoint(): Endpoint {
            if(endpoint ==null) {
                synchronized(this) {
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create()).build()
                        .create(Endpoint::class.java)
                }
            }
            return endpoint!!

        }


    }

}
