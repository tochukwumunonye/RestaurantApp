package com.tochukwu.restaurantapp.data.repository

import com.tochukwu.restaurantapp.data.remote.dto.RestaurantApi
import com.tochukwu.restaurantapp.data.remote.dto.RestaurantDto
import com.tochukwu.restaurantapp.domain.repository.RestaurantRepository
import com.tochukwu.restaurantapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RestaurantRepositoryImplementation  @Inject constructor(
    private val api : RestaurantApi
) : RestaurantRepository {

    override fun getListOfRestaurants(postCode: String): Flow<Resource<List<RestaurantDto.Restaurant>>> = flow  {


        try{
            // val apiResponse = api.getRestaurantsByPostCode(postCode).Restaurants.filter { it.isOpenNow }.map { it.toRestaurantDomain() }

            val apiResponse = api.getRestaurantsByPostCode(postCode).restaurants

            emit(Resource.success(apiResponse))

        }catch(e: IOException){

            emit(Resource.error(msg = "Couldnt reach server, check your internet connection", null))
        }catch(e: HttpException){
            emit(Resource.error(msg = "Oops, something went wrong!", null))
        }


    }
}