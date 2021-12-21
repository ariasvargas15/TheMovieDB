package com.bsav.themoviedb.framework.network.datasource

import com.bsav.themoviedb.data.movie.MovieRemoteDataSource
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.framework.network.services.MovieService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRemoteDataSourceImplTest {

    private val service = mockk<MovieService>()
    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var dataSource: MovieRemoteDataSource

    @Before
    fun setUp() {
        dataSource = MovieRemoteDataSourceImpl(service, dispatcher)
    }

    @Test
    fun `when getMovieById is called then should call service`() {
        val expected = mockk<Movie>()
        coEvery {
            service.getMovieById(1).mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getMovieById(1).first()
        }

        coVerify { service.getMovieById(1) }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getPopularMovies is called then should call service`() {
        val expected = listOf(mockk<Movie>())
        coEvery {
            service.getPopularMovies().mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getPopularMovies().first()
        }

        coVerify { service.getPopularMovies() }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getTopRatedMovies is called then should call service`() {
        val expected = listOf(mockk<Movie>())
        coEvery {
            service.getTopRatedMovies().mapToDomain()
        } answers {
            expected
        }

        val response = runBlocking {
            dataSource.getTopRatedMovies().first()
        }

        coVerify { service.getTopRatedMovies() }
        Assert.assertEquals(expected, response)
    }
}