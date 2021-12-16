package com.bsav.themoviedb.framework.di

import com.bsav.themoviedb.framework.network.services.PopularMoviesService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    @Reusable
    fun providesPopularMoviesService(retrofit: Retrofit): PopularMoviesService =
        retrofit.create(PopularMoviesService::class.java)

}