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
            genreIds = emptyList(),
            id = 12345,
            originalLanguage = "US",
            originalTitle = "Spiderman, No Way Home",
            overview = "This is an amazing movie",
            popularity = 9.9,
            posterPath = "posterPath",
            releaseDate = "15-12-2021",
            title = "Spiderman, No way home",
            video = false,
            voteAverage = 9.9,
            voteCount = 143
        )

        val expected = Program(12345, "posterPath", ProgramType.Movie)

        val response = mapper.movieToProgram(movie)

        Assert.assertEquals(expected, response)
    }
}