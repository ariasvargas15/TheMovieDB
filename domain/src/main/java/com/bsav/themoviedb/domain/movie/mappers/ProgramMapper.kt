package com.bsav.themoviedb.domain.movie.mappers

import com.bsav.themoviedb.domain.Program
import com.bsav.themoviedb.domain.movie.Movie
import javax.inject.Inject

class ProgramMapper @Inject constructor() {

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