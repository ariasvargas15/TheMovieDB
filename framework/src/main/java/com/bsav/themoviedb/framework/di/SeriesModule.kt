package com.bsav.themoviedb.framework.di

import com.bsav.themoviedb.data.serie.SerieLocalDataSource
import com.bsav.themoviedb.data.serie.SerieRemoteDataSource
import com.bsav.themoviedb.data.serie.SerieRepositoryImpl
import com.bsav.themoviedb.domain.serie.usecase.SerieRepository
import com.bsav.themoviedb.framework.db.TheMovieDBDatabase
import com.bsav.themoviedb.framework.db.daos.SerieDao
import com.bsav.themoviedb.framework.db.datasource.SerieLocalDataSourceImpl
import com.bsav.themoviedb.framework.network.datasource.SerieRemoteDataSourceImpl
import com.bsav.themoviedb.framework.network.services.SerieService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class SeriesModule {

    @Provides
    @Reusable
    fun providesRepository(serieLocalDataSource: SerieLocalDataSource, serieRemoteDataSource: SerieRemoteDataSource): SerieRepository =
        SerieRepositoryImpl(serieRemoteDataSource, serieLocalDataSource)


    @Provides
    @Reusable
    fun providesLocalDataSource(serieDao: SerieDao): SerieLocalDataSource = SerieLocalDataSourceImpl(serieDao)

    @Provides
    @Reusable
    fun providesSerieDao(theMovieDBDatabase: TheMovieDBDatabase) = theMovieDBDatabase.serieDao()

    @Provides
    @Reusable
    fun providesRemoteDataSource(serieService: SerieService): SerieRemoteDataSource = SerieRemoteDataSourceImpl(serieService)

    @Provides
    @Reusable
    fun providesSerieService(retrofit: Retrofit) = retrofit.create(SerieService::class.java)
}