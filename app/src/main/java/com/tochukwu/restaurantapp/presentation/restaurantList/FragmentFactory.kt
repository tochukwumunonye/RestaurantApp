package com.tochukwu.restaurantapp.presentation.restaurantList

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class FragmentFactory @Inject constructor(
    private val  restaurantAdapter: RestaurantAdapter
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            PlaceList::class.java.name -> PlaceList(restaurantAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}