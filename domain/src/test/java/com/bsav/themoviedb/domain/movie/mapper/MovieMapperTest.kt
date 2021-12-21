package com.bsav.themoviedb.domain.movie.mapper

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import org.junit.Assert
import org.junit.Test


class MovieMapperTest {

    private val mapper = MovieMapper()

    @Test
    fun `given a movie when movieToProgram is called then should map to Program`() {
        val movie = Movie(
            adult = false,
            backdropPath = "path",
            id = 12345,
            originalLanguage = "US",
            originalTitle = "Spiderman",
            overview = "This is an amazing movie",
            popularity = 9.9,
            posterPath = "posterPath",
            releaseDate = "15-12-2021",
            title = "Spiderman",
            video = false,
            voteAverage = 9.9,
            voteCount = 143
        )

        val expected = Program(
            id = 12345,
            posterPath = "posterPath",
            name = "Spiderman",
            type = ProgramType.Movie.Popular
        )

        val response = mapper.movieToProgram(movie, ProgramType.Movie.Popular)

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given a program when programToMovie is called then should map to Movie`() {
        val program = Program(
            id = 12345,
            name = "Spiderman",
            posterPath = "posterPath",
            type = ProgramType.Movie.Popular
        )

        val expected = Movie(
            id = 12345,
            posterPath = "posterPath",
            title = "Spiderman",
        )

        val response = mapper.programToMovie(program)

        Assert.assertEquals(expected, response)
    }

}