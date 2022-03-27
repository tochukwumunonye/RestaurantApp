package com.tochukwu.restaurantapp.data.remote.dto

import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {


    @GET("/restaurants/bypostcode/{postcode}")
    suspend fun getRestaurantsByPostCode(@Path("postcode") postcode: String): RestaurantDto


    companion object {
        const val BASE_URL = "https://uk.api.just-eat.io/"
    }

}
