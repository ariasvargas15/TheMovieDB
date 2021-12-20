package com.bsav.themoviedb.framework.di

import com.bsav.themoviedb.data.search.SearchRemoteDataSource
import com.bsav.themoviedb.data.search.SearchRepositoryImpl
import com.bsav.themoviedb.domain.search.repository.SearchRepository
import com.bsav.themoviedb.framework.network.datasource.SearchRemoteDataSourceImpl
import com.bsav.themoviedb.framework.network.services.SearchService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    @Reusable
    fun providesSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    @Reusable
    fun providesSearchRepository(remoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(remoteDataSource)

    @Provides
    @Reusable
    fun providesSearchRemoteDataSource(service: SearchService): SearchRemoteDataSource = SearchRemoteDataSourceImpl(service)

}