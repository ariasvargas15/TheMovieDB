package com.bsav.themoviedb.data.movie

import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.repository.MovieRepository
import com.bsav.themoviedb.domain.program.ProgramType
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieRepositoryImplTest {

    private val localDataSource = mockk<MovieLocalDataSource>(relaxed = true)
    private val remoteDataSource = mockk<MovieRemoteDataSource>()
    private lateinit var repository: MovieRepository

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `given remoteDataSource response ok when getMovieById then should emit the response`() {
        val expected = mockk<Movie>()
        every {
            remoteDataSource.getMovieById(1)
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getMovieById(1).first()
        }

        coVerify(exactly = 1) {
            remoteDataSource.getMovieById(1)
            localDataSource.saveMovie(expected)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response fails when getMovieById then should call localDataSource`() {
        val expected = mockk<Movie>()
        every {
            remoteDataSource.getMovieById(1)
        } answers {
            flow {
                throw Exception()
            }
        }
        every {
            localDataSource.getMovieById(1)
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getMovieById(1).first()
        }
        verify(exactly = 1) {
            remoteDataSource.getMovieById(1)
            localDataSource.getMovieById(1)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response ok when getPopularMovies then should emit the response`() {
        val expected = mockk<List<Movie>>()
        every {
            remoteDataSource.getPopularMovies()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getPopularMovies().first()
        }

        coVerify(exactly = 1) {
            remoteDataSource.getPopularMovies()
            localDataSource.deleteMoviesByType(ProgramType.Movie.Popular)
            localDataSource.savePopularMovies(expected)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response fails when getPopularMovies then should call localDataSource`() {
        val expected = mockk<List<Movie>>()
        every {
            remoteDataSource.getPopularMovies()
        } answers {
            flow {
                throw Exception()
            }
        }
        every {
            localDataSource.getPopularMovies()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getPopularMovies().first()
        }
        verify(exactly = 1) {
            remoteDataSource.getPopularMovies()
            localDataSource.getPopularMovies()
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response ok when getTopRatedMovies then should emit the response`() {
        val expected = mockk<List<Movie>>()
        every {
            remoteDataSource.getTopRatedMovies()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getTopRatedMovies().first()
        }

        coVerify(exactly = 1) {
            remoteDataSource.getTopRatedMovies()
            localDataSource.deleteMoviesByType(ProgramType.Movie.TopRated)
            localDataSource.saveTopRatedMovies(expected)
        }

        Assert.assertEquals(expected, response)
    }

    @Test
    fun `given remoteDataSource response fails when getTopRatedMovies then should call localDataSource`() {
        val expected = mockk<List<Movie>>()
        every {
            remoteDataSource.getTopRatedMovies()
        } answers {
            flow {
                throw Exception()
            }
        }
        every {
            localDataSource.getTopRatedMovies()
        } answers {
            flowOf(expected)
        }

        val response = runBlocking {
            repository.getTopRatedMovies().first()
        }
        verify(exactly = 1) {
            remoteDataSource.getTopRatedMovies()
            localDataSource.getTopRatedMovies()
        }

        Assert.assertEquals(expected, response)
    }
}