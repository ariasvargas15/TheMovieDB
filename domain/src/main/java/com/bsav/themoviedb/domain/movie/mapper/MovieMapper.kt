package com.bsav.themoviedb.domain.movie.mapper

import com.bsav.themoviedb.domain.Program
import com.bsav.themoviedb.domain.movie.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun movieToProgram(movie: Movie): Program {
        with(movie) {
            return Program(
                backdropPath,
                id,
                originalTitle,
                popularity,
                posterPath,
                releaseDate,
                title,
                voteAverage,
                voteCount
            )
        }
    }
}