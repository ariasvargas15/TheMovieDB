package com.bsav.themoviedb.framework.di

import com.bsav.themoviedb.data.movie.MovieLocalDataSource
import com.bsav.themoviedb.data.movie.MovieRemoteDataSource
import com.bsav.themoviedb.data.movie.MovieRepositoryImpl
import com.bsav.themoviedb.domain.movie.mapper.MovieMapper
import com.bsav.themoviedb.domain.movie.repository.MovieRepository
import com.bsav.themoviedb.framework.db.TheMovieDBDatabase
import com.bsav.themoviedb.framework.db.datasource.MovieLocalDataSourceImpl
import com.bsav.themoviedb.framework.network.datasource.MovieRemoteDataSourceImpl
import com.bsav.themoviedb.framework.network.services.MovieService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Provides
    @Reusable
    fun providesMoviesService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @Provides
    @Reusable
    fun providesMovieRepository(localDataSource: MovieLocalDataSource, remoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @Reusable
    fun providesMovieRemoteDataSource(service: MovieService): MovieRemoteDataSource = MovieRemoteDataSourceImpl(service)

    @Provides
    @Reusable
    fun providesMovieLocalDataSource(database: TheMovieDBDatabase, mapper: MovieMapper): MovieLocalDataSource =
        MovieLocalDataSourceImpl(database.programDao(), mapper)
}