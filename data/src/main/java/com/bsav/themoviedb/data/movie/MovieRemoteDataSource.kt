package com.bsav.themoviedb.data.movie

import com.bsav.themoviedb.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getTopRatedMovies(): Flow<List<Movie>>
}
