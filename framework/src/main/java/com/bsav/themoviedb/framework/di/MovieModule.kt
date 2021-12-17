package com.bsav.themoviedb.framework.di

import com.bsav.themoviedb.data.movie.MovieLocalDataSource
import com.bsav.themoviedb.data.movie.MovieRemoteDataSource
import com.bsav.themoviedb.data.movie.MovieRepositoryImpl
import com.bsav.themoviedb.domain.movie.usecases.MovieRepository
import com.bsav.themoviedb.framework.db.datasource.MovieLocalDataSourceImpl
import com.bsav.themoviedb.framework.network.datasource.MovieRemoteDataSourceImpl
import com.bsav.themoviedb.framework.network.services.PopularMoviesService
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
    fun providesPopularMoviesService(retrofit: Retrofit): PopularMoviesService =
        retrofit.create(PopularMoviesService::class.java)

    @Provides
    @Reusable
    fun providesMovieRepository(localDataSource: MovieLocalDataSource, remoteDataSource: MovieRemoteDataSource): MovieRepository =
        MovieRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @Reusable
    fun providesMovieRemoteDataSource(service: PopularMoviesService): MovieRemoteDataSource = MovieRemoteDataSourceImpl(service)

    @Provides
    @Reusable
    fun providesMovieLocalDataSource(): MovieLocalDataSource = MovieLocalDataSourceImpl()
}