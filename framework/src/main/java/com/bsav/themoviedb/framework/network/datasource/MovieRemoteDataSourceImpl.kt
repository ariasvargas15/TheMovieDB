package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.movie.MovieRemoteDataSource
import com.bsav.themoviedb.domain.movie.Movie
import com.bsav.themoviedb.framework.network.services.PopularMoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRemoteDataSourceImpl(private val service: PopularMoviesService) : MovieRemoteDataSource {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flow {
            emit(service.getPopularMovies(1).toDomain().results)
        }
    }
}