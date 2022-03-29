package com.tochukwu.restaurantapp.domain.repository


import com.tochukwu.restaurantapp.data.remote.dto.RestaurantDto
import com.tochukwu.restaurantapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface RestaurantRepository {

    fun getListOfRestaurants(postCode : String) : Flow<Resource<List<RestaurantDto.Restaurant>>>

}
