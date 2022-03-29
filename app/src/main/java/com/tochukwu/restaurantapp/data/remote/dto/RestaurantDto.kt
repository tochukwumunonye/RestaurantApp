package com.tochukwu.restaurantapp.data.remote.dto

import com.google.gson.annotations.SerializedName


data class RestaurantDto(
    @SerializedName("Area")
    val area: String,
    @SerializedName("Metadata")
    val metadata: Metadata,
    @SerializedName("Restaurants")
    val restaurants: List<Restaurant>

){
    data class Restaurant(
        @SerializedName("Id")
        val id: Int,
        @SerializedName("Name")
        val name: String,
        @SerializedName("RatingStars")
        val ratingStars: Double,
        @SerializedName("NumberOfRatings")
        val numberOfRatings: Int,
        @SerializedName("RatingAverage")
        val ratingAverage: Double,
        @SerializedName("Rating")
        val rating: Rating,
        @SerializedName("LogoUrl")
        val imgUrl: String,
        @SerializedName("IsOpenNow")
        val isOpen: Boolean,
        @SerializedName("CuisineTypes")
        val cuisineTypes: List<CuisineType>

    )


}


data class Metadata(
    @SerializedName("CanonicalName")
    val canonicalName: String,
    @SerializedName("Postcode")
    val postcode: String,
    @SerializedName("Area")
    val area: String
)




data class Rating(
    @SerializedName("Count")
    val ratingCount: Int,
    @SerializedName("Average")
    val average: Double,
    @SerializedName("StarRating")
    val starRating: Double
)

data class CuisineType(
    @SerializedName("Id")
    val cuisineTypeId: Int,
    @SerializedName("IsTopCuisine")
    val isTopCuisine: Boolean,
    @SerializedName("Name")
    val cuisineName: String
)