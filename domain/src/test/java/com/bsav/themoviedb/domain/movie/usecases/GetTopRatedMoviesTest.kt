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

class GetTopRatedMoviesTest {

    private val movieRepository = mockk<MovieRepository>()
    private lateinit var getTopRatedMovies: GetTopRatedMovies

    @Before
    fun setUp() {
        getTopRatedMovies = GetTopRatedMovies(movieRepository)
    }

    @Test
    fun `when invoke is called then should call repository and return its answer`() {
        val expected = listOf(mockk<Movie>())

        every {
            movieRepository.getTopRatedMovies()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            getTopRatedMovies().first()
        }

        verify(exactly = 1) {
            movieRepository.getTopRatedMovies()
        }
        Assert.assertEquals(expected, response)
    }
}
