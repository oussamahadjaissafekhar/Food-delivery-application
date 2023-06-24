package com.example.movieexample.retrofit

import com.example.RestaurentApp.entity.Menu
import com.example.RestaurentApp.entity.OrderRequest
import com.example.RestaurentApp.entity.Restaurent
import com.example.RestaurentApp.entity.user
import com.example.RestaurentApp.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Endpoint {

    @GET("restaurent/getall")
    suspend fun getAllRestaurents(): Response<List<Restaurent>>
    @GET("getMenu")
    suspend fun getMenu(@Query("restaurentId") restaurentId: Int): Response<List<Menu>>
    @FormUrlEncoded
    @POST("authenticate")
    suspend fun authentification(@Field("username") username: String,
                                 @Field("password") password: String): Response<user>
    @POST("addOrder")
    suspend fun createOrder(@Body orderRequest: OrderRequest): Response<Any>
    @FormUrlEncoded
    @POST("addUser")
    suspend fun createUser(@Field("user") user: String): Response<Any>

    @POST("addRating")
    suspend fun createRating(@Body rating: String): Response<Any>
    @FormUrlEncoded
    @POST("addRating")
    suspend fun submitRating(
        @Field("user_id") userId: String,
        @Field("restaurant_id") restaurantId: String,
        @Field("rating") rating: String,
        @Field("review") review: String
    ): Response<Void>
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