package com.bsav.themoviedb.domain.tvshow.mapper

import com.bsav.themoviedb.domain.program.Program
import com.bsav.themoviedb.domain.program.ProgramType
import com.bsav.themoviedb.domain.tvshow.model.TvShow
import org.junit.Assert
import org.junit.Test

class TvShowMapperTest {

    private val mapper = TvShowMapper()

    @Test
    fun `given a tvShow when tvShowToProgram is called then should map to Program`() {
        val tvShow = TvShow(
            backdropPath = "path",
            firstAirDate = "15-01-2020",
            genreIds = emptyList(),
            id = 12345,
            name = "The 100",
            originCountry = listOf("US"),
            originalLanguage = "US",
            originalName = "The 100",
            overview = "This is an amazing show",
            popularity = 9.9,
            posterPath = "posterPath",
            voteAverage = 9.9,
            voteCount = 143
        )

        val expected = Program(12345, "posterPath", ProgramType.TvShow)

        val response = mapper.tvShowToProgram(tvShow)

        Assert.assertEquals(expected, response)
    }
}