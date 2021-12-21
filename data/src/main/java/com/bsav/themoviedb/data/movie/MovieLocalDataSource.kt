package com.bsav.themoviedb.data.movie

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.program.ProgramType
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getTopRatedMovies(): Flow<List<Movie>>
    suspend fun savePopularMovies(movies: List<Movie>)
    suspend fun saveTopRatedMovies(movies: List<Movie>)
    suspend fun deleteMoviesByType(type: ProgramType.Movie)
}
