package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.movie.MovieRemoteDataSource
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.framework.network.services.MovieService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRemoteDataSourceImpl(
    private val service: MovieService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRemoteDataSource {

    override fun getMovieById(id: Int): Flow<Movie> = flow {
        emit(service.getMovieById(id).toDomain())
    }.flowOn(dispatcher)

    override fun getPopularMovies(): Flow<List<Movie>> = flow {
        emit(service.getPopularMovies().mapToDomain())
    }.flowOn(dispatcher)


    override fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        emit(service.getTopRatedMovies().mapToDomain())
    }.flowOn(dispatcher)

}