package com.bsav.themoviedb.domain.movie.usecases

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetTopRatedMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = movieRepository.getTopRatedMovies()
}