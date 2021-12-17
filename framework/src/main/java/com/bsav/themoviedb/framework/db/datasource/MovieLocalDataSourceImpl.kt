package com.bsav.themoviedb.framework.db.datasource

import com.bsav.themoviedb.data.movie.MovieLocalDataSource
import com.bsav.themoviedb.domain.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MovieLocalDataSourceImpl : MovieLocalDataSource {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return flowOf(emptyList())
    }

    override suspend fun savePopularMovies() {
        TODO("Not yet implemented")
    }
}