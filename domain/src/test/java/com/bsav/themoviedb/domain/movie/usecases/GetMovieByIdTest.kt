package com.bsav.themoviedb.domain.movie.usecases

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.repository.MovieRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetMovieByIdTest {

    private val movieRepository = mockk<MovieRepository>()
    private lateinit var getMovieById: GetMovieById

    @Before
    fun setUp() {
        getMovieById = GetMovieById(movieRepository)
    }

    @Test
    fun `when invoke is called then should call repository and return its answer`() {
        val expected = mockk<Movie>()

        every {
            movieRepository.getMovieById(1)
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            getMovieById(1).first()
        }

        verify(exactly = 1) {
            movieRepository.getMovieById(1)
        }
        Assert.assertEquals(expected, response)
    }
}