package com.bsav.themoviedb.domain.movie.usecases

import com.bsav.themoviedb.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<List<Movie>>
}