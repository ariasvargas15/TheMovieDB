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

class GetPopularMoviesTest {

    private val movieRepository = mockk<MovieRepository>()
    private lateinit var getPopularMovies: GetPopularMovies

    @Before
    fun setUp() {
        getPopularMovies = GetPopularMovies(movieRepository)
    }

    @Test
    fun `when invoke is called then should call repository and return its answer`() {
        val expected = listOf(mockk<Movie>())

        every {
            movieRepository.getPopularMovies()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            getPopularMovies().first()
        }

        verify(exactly = 1) {
            movieRepository.getPopularMovies()
        }
        Assert.assertEquals(expected, response)
    }
}