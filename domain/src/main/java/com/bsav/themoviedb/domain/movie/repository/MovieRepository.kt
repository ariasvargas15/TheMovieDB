package com.bsav.themoviedb.domain.movie.repository

import com.bsav.themoviedb.domain.movie.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieById(id: Int): Flow<Movie>
    fun getPopularMovies(): Flow<List<Movie>>
    fun getTopRatedMovies(): Flow<List<Movie>>
}