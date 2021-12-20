package com.bsav.themoviedb.framework.di

import com.bsav.themoviedb.data.tvshow.TvShowLocalDataSource
import com.bsav.themoviedb.data.tvshow.TvShowRemoteDataSource
import com.bsav.themoviedb.data.tvshow.TvShowRepositoryImpl
import com.bsav.themoviedb.domain.tvshow.repository.TvShowRepository
import com.bsav.themoviedb.framework.db.datasource.TvShowLocalDataSourceImpl
import com.bsav.themoviedb.framework.network.datasource.TvShowRemoteDataSourceImpl
import com.bsav.themoviedb.framework.network.services.TvShowService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class TvShowModule {

    @Provides
    @Reusable
    fun providesTvShowsService(retrofit: Retrofit): TvShowService =
        retrofit.create(TvShowService::class.java)

    @Provides
    @Reusable
    fun providesTvShowRepository(localDataSource: TvShowLocalDataSource, remoteDataSource: TvShowRemoteDataSource): TvShowRepository =
        TvShowRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @Reusable
    fun providesTvShowRemoteDataSource(service: TvShowService): TvShowRemoteDataSource = TvShowRemoteDataSourceImpl(service)

    @Provides
    @Reusable
    fun providesTvShowLocalDataSource(): TvShowLocalDataSource = TvShowLocalDataSourceImpl()
}