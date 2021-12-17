package com.bsav.themoviedb.data.movie

import com.bsav.themoviedb.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getPopularMovies(): Flow<List<Movie>>
    suspend fun savePopularMovies()
}
