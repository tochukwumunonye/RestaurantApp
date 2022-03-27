package com.tochukwu.restaurantapp.di

import com.tochukwu.restaurantapp.data.remote.dto.RestaurantApi
import com.tochukwu.restaurantapp.data.repository.RestaurantRepositoryImplementation
import com.tochukwu.restaurantapp.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRestaurantRepository(
        apiService: RestaurantApi
    ) = RestaurantRepositoryImplementation(
        api = apiService
    ) as RestaurantRepository
}

