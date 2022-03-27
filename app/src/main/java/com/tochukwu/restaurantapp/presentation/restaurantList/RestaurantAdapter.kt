package com.tochukwu.restaurantapp.presentation.restaurantList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tochukwu.restaurantapp.data.remote.dto.RestaurantDto
import com.tochukwu.restaurantapp.databinding.ItemRestaurantBinding
import javax.inject.Inject

class RestaurantAdapter @Inject constructor(

): androidx.recyclerview.widget.ListAdapter<RestaurantDto.Restaurant, RestaurantAdapter.RestaurantViewHolder>(DIFF_COMPARATOR){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null){
            holder.bind(currentItem)
        }
    }




    inner class RestaurantViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(place: RestaurantDto.Restaurant){
            binding.apply{
                Glide.with(itemView).load(place.logoUrl).into(poster)
            }
        }
    }

    companion object {
        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<RestaurantDto.Restaurant>() {
            override fun areItemsTheSame(oldItem: RestaurantDto.Restaurant, newItem: RestaurantDto.Restaurant): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RestaurantDto.Restaurant,
                newItem: RestaurantDto.Restaurant
            ): Boolean {
                return oldItem.logoUrl == newItem.logoUrl
            }
        }
    }

}