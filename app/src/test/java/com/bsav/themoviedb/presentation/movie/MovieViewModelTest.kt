package com.bsav.themoviedb.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bsav.themoviedb.MainCoroutineRule
import com.bsav.themoviedb.domain.movie.model.Movie
import com.bsav.themoviedb.domain.movie.usecases.GetMovieById
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getMovieById = mockk<GetMovieById>()
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        viewModel = MovieViewModel(getMovieById)
    }

    @Test
    fun `when getMovie is called then should modify liveData`() {
        val movie = mockk<Movie>()
        every {
            getMovieById(1)
        } answers {
            flowOf(movie)
        }

        viewModel.getMovie(1)

        verify { getMovieById(1) }
        Assert.assertEquals(movie, viewModel.movie.value)
    }
}