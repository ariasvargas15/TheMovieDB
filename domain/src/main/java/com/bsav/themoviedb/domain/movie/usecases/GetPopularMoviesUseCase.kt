package com.bsav.themoviedb.domain.movie.usecases

import com.bsav.themoviedb.domain.movie.Movie
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = movieRepository.getPopularMovies()
}