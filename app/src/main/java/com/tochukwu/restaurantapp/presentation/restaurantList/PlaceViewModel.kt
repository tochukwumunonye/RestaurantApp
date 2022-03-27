package com.tochukwu.restaurantapp.presentation.restaurantList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tochukwu.restaurantapp.data.remote.dto.RestaurantDto
import com.tochukwu.restaurantapp.domain.repository.RestaurantRepository
import com.tochukwu.restaurantapp.util.Event
import com.tochukwu.restaurantapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val restaurantRepository : RestaurantRepository
)  : ViewModel(){


    private val _restaurantChannel = MutableLiveData<Event<Resource<List<RestaurantDto.Restaurant>>>>()
    val restaurantChannel: LiveData<Event<Resource<List<RestaurantDto.Restaurant>>>> get() = _restaurantChannel

    fun getRestaurants(postCode : String){
        _restaurantChannel.postValue(Event(Resource.loading(null)))
        if(postCode.isBlank()){
            _restaurantChannel.postValue(Event(Resource.error("The field must not be empty", null)))
            return
        }
        getRestaurantsFromNetwork(postCode)
    }

    private fun  getRestaurantsFromNetwork(postCode: String) = viewModelScope.launch{
        restaurantRepository.getListOfRestaurants(postCode).collectLatest {
            _restaurantChannel.postValue(Event(it))
        }
    }
}
