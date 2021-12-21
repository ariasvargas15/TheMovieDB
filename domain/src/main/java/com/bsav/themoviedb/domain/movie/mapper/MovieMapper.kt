package com.bsav.themoviedb.domain.movie.mapper

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun movieToProgram(movie: Movie, type: ProgramType.Movie): Program {
        with(movie) {
            return Program(
                id,
                title,
                posterPath,
                type
            )
        }
    }

    fun programToMovie(program: Program): Movie {
        with(program) {
            return Movie(
                id = id,
                posterPath = posterPath,
                title = name
            )
        }
    }
}