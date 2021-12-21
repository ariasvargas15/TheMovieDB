package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.movie.MovieRemoteDataSource
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.framework.network.services.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRemoteDataSourceImpl(private val service: MovieService) : MovieRemoteDataSource {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            emit(service.getPopularMovies().mapToDomain())
        }
    }

    override fun getTopRatedMovies(): Flow<List<Movie>> {
        return flow {
            emit(service.getTopRatedMovies().mapToDomain())
        }
    }
}